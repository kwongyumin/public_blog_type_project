package com.example.blog.model.user;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column
    private Long kakaoId;

    @Column
    private String userName;

    @Column
    private String nickName;

    @Column
    private String email;

    // fixme :: enum 으로 변경
    @Column
    private String loginType;


    @Column
    @LastModifiedDate // 수정 시간 자동 업데이트
    private LocalDateTime modTime;

    @Column(updatable = false)
    @CreatedDate // 등록 시간 자동 업데이트
    private LocalDateTime regTime;

}
