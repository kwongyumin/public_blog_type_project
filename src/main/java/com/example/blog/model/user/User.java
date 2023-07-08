package com.example.blog.model.user;


import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

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
}
