package com.example.blog.common.util;

import com.example.blog.common.codes.ErrorCode;
import com.example.blog.config.exception.BusinessExceptionHandler;
import com.example.blog.dto.user.UserDto;
import com.example.blog.service.user.UserService;
import com.example.blog.service.user.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 해당 클래스는 JWT에서 사용되는 토큰 관련 유틸들을 관리하는 클래스
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class TokenUtils {

    @PostConstruct
    public void init() {
        jwtSecretKey = getJwtSecretKey;
    }

    private static UserService userService;

    @Value(value = "${jwt.secret-key}")
    private String getJwtSecretKey;

    private static String jwtSecretKey;

    private static UserDetailsServiceImpl userDetailsService;
    /**
     * 사용자 정보를 기반으로 토큰을 생성하여 반환 해주는 메서드
     *
     * @param  userDto : 사용자 정보
     * @return String : 토큰
     */
    public static String generateJwtToken(UserDto userDto) {
        // 사용자 시퀀스를 기준으로 JWT 토큰을 발급하여 반환해줍니다.
        JwtBuilder builder = Jwts.builder()
                .setHeader(createHeader())                              // Header 구성
                .setClaims(createClaims(userDto))                       // Payload - Claims 구성
                .setSubject(String.valueOf(userDto.getUserEmail()))        // Payload - Subject 구성
                .signWith(SignatureAlgorithm.HS256, createSignature())  // Signature 구성
                .setExpiration(createExpiredDate());                    // Expired Date 구성
        return builder.compact();
    }


    /**
     * 토큰을 기반으로 사용자 정보를 반환 해주는 메서드
     *
     * @param token String : 토큰
     * @return String : 사용자 정보
     */
    public static String parseTokenToUserInfo(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * 유효한 토큰인지 확인 해주는 메서드
     *
     * @param token String  : 토큰
     * @return boolean      : 유효한지 여부 반환
     */
    public static boolean isValidToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);

            log.info("expireTime :" + claims.getExpiration());
            log.info("userPk :" + claims.get("userPk") );
            log.info("userEmail :" + claims.get("userEmail"));
            log.info("userNm :" + claims.get("userNm"));

            return true;
        } catch (ExpiredJwtException exception) {
            log.error("Token Expired");
            return false;
        } catch (JwtException exception) {
            log.error("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            log.error("Token is null");
            return false;
        }
    }

    /**
     * Header 내에 토큰을 추출합니다.
     *
     * @param header 헤더
     * @return String
     */
    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }


    /**
     * 토큰의 만료기간을 지정하는 함수
     *
     * @return Calendar
     */
    private static Date createExpiredDate() {
        // 토큰 만료시간은 30일으로 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 8);     // 8시간
        // c.add(Calendar.DATE, 1);         // 1일
        return c.getTime();
    }

    /**
     * JWT의 "헤더" 값을 생성해주는 메서드
     *
     * @return HashMap<String, Object>
     */
    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    /**
     * 사용자 정보를 기반으로 클래임을 생성해주는 메서드
     *
     * @param userDto 사용자 정보
     * @return Map<String, Object>
     */
    private static Map<String, Object> createClaims(UserDto userDto) {
        // 공개 클레임에 사용자의 이름과 이메일을 설정하여 정보를 조회할 수 있다.
        Map<String, Object> claims = new HashMap<>();

        log.info("userId :" + userDto.getUserId() );
        log.info("userEmail :" + userDto.getUserEmail());
        log.info("userName :" + userDto.getUserName());
        log.info("authorities : " + userDto.getAuthorityList());
        // NOTE : user pk 와 로그인 id 값인 이메일값을 클레임 셋팅 , 필요에 의해 추가 예정
        claims.put("userId", userDto.getUserId());
        claims.put("userEmail", userDto.getUserEmail());
        claims.put("userName",userDto.getUserName());
        claims.put("authorities", userDto.getAuthorityList());
        return claims;
    }


    /**
     * JWT "서명(Signature)" 발급을 해주는 메서드
     *
     * @return Key
     */
    private static Key createSignature() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * 토큰 정보를 기반으로 Claims 정보를 반환받는 메서드
     *
     * @param token : 토큰
     * @return Claims : Claims
     */
    private static Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
                .parseClaimsJws(token).getBody();
    }

    /**
     * 토큰을 기반으로 사용자 정보를 반환받는 메서드
     *
     * @param token : 토큰
     * @return String : 사용자 아이디 (이메일)
     */
    public static String getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("userEmail").toString();
    }

    /**
     *  토큰을 복호화하여 Authentication 객체를 만들어 반환
     *
     * @param token : 토큰
     * @return String : 사용자 정보
     */
    public static Authentication getAuthentication(String token) {
        // 토큰 복호화
        Claims claims = getClaimsFromToken(token);

        if (claims.get("authorities") == null) {
            throw new RuntimeException(ErrorCode.NOT_VALID_AUTHORITIES.getMessage());
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("authorities").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.get("userEmail").toString());
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    /**
     * SecurityContextHolder 에 저장된 사용자 정보를 반환한다.
     * @return Long : 사용자 pk
     */
    public static UserDto getUserFromAuthentication() {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = UserDto.builder()
                .userEmail(userDetails.getUsername())
                .build();

        return userService.findUserByEmail(userDto).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.NOT_FOUND_USER.getMessage() , ErrorCode.NOT_FOUND_USER));
    }

}
