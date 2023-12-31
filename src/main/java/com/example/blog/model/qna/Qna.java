package com.example.blog.model.qna;

import com.example.blog.dto.qna.QnaRequestDto;
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
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private String qnaTitle;

    @Column
    private String qnaContents;

    @Column
    @LastModifiedDate // 수정 시간 자동 업데이트
    private LocalDateTime modTime;

    @Column(updatable = false)
    @CreatedDate // 등록 시간 자동 업데이트
    private LocalDateTime regTime;

    public static Qna createQna(Long userId , QnaRequestDto.CreateQna requestDto) {
        return Qna.builder()
                .userId(userId)
                .qnaTitle(requestDto.getQnaTitle())
                .qnaContents(requestDto.getQnaContents())
                .build();
    }
}
