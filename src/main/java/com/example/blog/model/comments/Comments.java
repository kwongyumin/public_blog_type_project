package com.example.blog.model.comments;

import com.example.blog.dto.comments.CommentsRequestDto;
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
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long writeTypeId;

    @Column
    private WriteType writeType;

    @Column
    private String contents;

    @Column
    @LastModifiedDate // 수정 시간 자동 업데이트
    private LocalDateTime modTime;

    @Column(updatable = false)
    @CreatedDate // 등록 시간 자동 업데이트
    private LocalDateTime regTime;

    public static Comments createComments(Long userId, CommentsRequestDto.CreateComments requestDto) {
        return Comments.builder()
                .userId(userId)
                .writeType(requestDto.getWriteType())
                .writeTypeId(requestDto.getWriteTypeId())
                .contents(requestDto.getContents())
                .build();
    }


}
