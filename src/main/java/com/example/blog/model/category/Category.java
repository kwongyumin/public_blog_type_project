package com.example.blog.model.category;

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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String categoryName;

    // Blog 와 연관관걔 추가 필요 , one to many

    @Column
    @LastModifiedDate // 수정 시간 자동 업데이트
    private LocalDateTime modTime;

    @Column(updatable = false)
    @CreatedDate // 등록 시간 자동 업데이트
    private LocalDateTime regTime;
}
