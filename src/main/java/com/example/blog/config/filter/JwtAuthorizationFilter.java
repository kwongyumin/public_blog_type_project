package com.example.blog.config.filter;

/**
 *  해당 클래스는 Spring Security의 환경설정을 구성하는 단계에서 필터로 등록한 클래스이며, 지정한 URL 별 JWT 유효성 검증을 수행하며 직접적인 사용자 '인증'을 확인합니다.
 */

import com.example.blog.common.constants.AuthConstants;
import com.example.blog.common.util.TokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 지정한 URL 별 JWT 유효성 검증을 수행하며 직접적인 사용자 '인증'을 확인합니다.
 * @fileName JwtAuthorizationFilter
 */
@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws IOException, ServletException {

        // 1. 토큰이 필요하지 않은 API URL에 대해서 배열로 구성합니다.
        List<String> list = Arrays.asList("/user/login" , "/user/join");


        // 2. 토큰이 필요하지 않은 API URL의 경우 => 로직 처리 없이 다음 필터로 이동
        if (list.contains(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        // 3. OPTIONS 요청일 경우 => 로직 처리 없이 다음 필터로 이동
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            chain.doFilter(request, response);
            return;
        }

        // [STEP1] Client에서 API를 요청할때 Header를 확인합니다.
        String header = request.getHeader(AuthConstants.AUTH_HEADER);
        logger.debug("[+] header Check: " + header);

        try {
            // [STEP2-1] Header 내에 토큰이 존재하는 경우
            if (header != null && !header.equalsIgnoreCase("")) {

                // [STEP2] Header 내에 토큰을 추출합니다.
                String token = TokenUtils.getTokenFromHeader(header);

                // [STEP3] 추출한 토큰이 유효한지 여부를 체크합니다.
                if (TokenUtils.isValidToken(token)) {

                    // [STEP4] 토큰을 기반으로 사용자 아이디를 반환 받는 메서드
                    String userEmail = TokenUtils.getUserIdFromToken(token);
                    logger.debug("[+] userId(email) Check: " + userEmail);

                    // [STEP5] 사용자 아이디가 존재하는지 여부 체크
                    if (userEmail != null && !userEmail.equalsIgnoreCase("")) {
                        chain.doFilter(request, response);
                    } else {
                       // throw new BusinessExceptionHandler("TOKEN isn't userId", ErrorCode.BUSINESS_EXCEPTION_ERROR);
                    }
                    // 토큰이 유효하지 않은 경우
                } else {
                   // throw new BusinessExceptionHandler("TOKEN is invalid", ErrorCode.BUSINESS_EXCEPTION_ERROR);
                }
            }
            // [STEP2-1] 토큰이 존재하지 않는 경우
            else {
               // throw new BusinessExceptionHandler("Token is null", ErrorCode.BUSINESS_EXCEPTION_ERROR);
            }
        } catch (Exception e) {
            // Token 내에 Exception이 발생 하였을 경우 => 클라이언트에 응답값을 반환하고 종료합니다.
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            JSONObject jsonObject = jsonResponseWrapper(e);
            printWriter.print(jsonObject);
            printWriter.flush();
            printWriter.close();
        }
    }

    /**
     * 토큰 관련 Exception 발생 시 예외 응답값 구성
     *
     * @param e Exception
     * @return JSONObject
     */
    private JSONObject jsonResponseWrapper(Exception e) {

        String resultMsg = "";
        // JWT 토큰 만료
        if (e instanceof ExpiredJwtException) {
            resultMsg = "TOKEN Expired";
        }
        // JWT 허용된 토큰이 아님
        else if (e instanceof SignatureException) {
            resultMsg = "TOKEN SignatureException Login";
        }
        // JWT 토큰내에서 오류 발생 시
        else if (e instanceof JwtException) {
            resultMsg = "TOKEN Parsing JwtException";
        }
        // 이외 JTW 토큰내에서 오류 발생
        else {
            resultMsg = "OTHER TOKEN ERROR";
        }

        HashMap<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("status", 401);
        jsonMap.put("code", "9999");
        jsonMap.put("message", resultMsg);
        jsonMap.put("reason", e.getMessage());
        JSONObject jsonObject = new JSONObject(jsonMap);
        logger.error(resultMsg, e);
        return jsonObject;
    }
}