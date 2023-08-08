package com.example.blog.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private AuthType authType;

    @JoinColumn(name = "user")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @Column
    @LastModifiedDate // 수정 시간 자동 업데이트
    private LocalDateTime modTime;

    @Column(updatable = false)
    @CreatedDate // 등록 시간 자동 업데이트
    private LocalDateTime regTime;

    public void setUser(User user) {
        this.user = user;
    }

}
