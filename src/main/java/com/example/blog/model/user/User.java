package com.example.blog.model.user;


import com.example.blog.common.constants.AuthConstants;
import com.example.blog.dto.auth.OAuthInfoResponse;
import com.example.blog.dto.user.UserRequestDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EnableJpaAuditing // Auditing 활성화
@EntityListeners(AuditingEntityListener.class) // Auditing 리스너 등록
public class User {

    /*
       fixme : 데이터모델링 전 , db 연동 테스트를 위한 IDX 값 생성
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // fixme :: naver 추가 예정, sns 로그인 관련 테이블 분리에 대한 생각 필요

    @Column
    private Long kakaoId;

    @Column
    private OAuthType oAuthType;

    @Column
    private String userName;

    @Column
    private String nickName;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> roles = new ArrayList<>();

    @Column
    @LastModifiedDate // 수정 시간 자동 업데이트
    private LocalDateTime modTime;

    @Column(updatable = false)
    @CreatedDate // 등록 시간 자동 업데이트
    private LocalDateTime regTime;

    public void setRoles(List<Authority> role) {
        this.roles = role;
        role.forEach(o -> o.setUser(this));
    }

    /**
     *  일반 유저 정보 셋팅
     */
    public static User setDefaultUser(UserRequestDto.JoinUser requestDto, PasswordEncoder passwordEncoder){
        User defaultUser = User.builder()
                .oAuthType(OAuthType.DEFAULT)
                .userName(requestDto.getUserName())
                .nickName(requestDto.getNickName())
                .password(passwordEncoder.encode(requestDto.getUserPassword()))
                .email(requestDto.getUserEmail())
                .build();
        defaultUser.setRoles(buildUserAuthority());

        return defaultUser;
    }

    /**
     *  OAuth 정보 기반 유저 정보 셋팅
     */
    public static User setOAuthUser(OAuthInfoResponse oAuthInfoResponse){
        User oAuthUser = User.builder()
                .email(oAuthInfoResponse.getUserEmail())
                .userName(oAuthInfoResponse.getUserName())
                .nickName(oAuthInfoResponse.getNickname())
                .oAuthType(oAuthInfoResponse.getOAuthType())
                .build();
        oAuthUser.setRoles(buildUserAuthority());
        return oAuthUser;
    }

    /**
     *  유저권한 셋팅
     */
    private static List<Authority> buildUserAuthority() {
        List<Authority> authList = new ArrayList<>();
        Authority auth = Authority.builder()
                .authorityName(AuthConstants.ROLE_USER)
                .build();
        authList.add(auth);
        return authList;
    }


}
