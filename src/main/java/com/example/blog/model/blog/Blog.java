package com.example.blog.model.blog;


import com.example.blog.dto.blog.BlogRequestDto;
import com.example.blog.model.category.Category;
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
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private String blogTitle;

    @Column
    private String blogContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Category category;

    @Column
    @LastModifiedDate // 수정 시간 자동 업데이트
    private LocalDateTime modTime;

    @Column(updatable = false)
    @CreatedDate // 등록 시간 자동 업데이트
    private LocalDateTime regTime;

    public void setCategory(Category category) {
        if (this.category != null) {
            this.category.getBlogs().remove(this);
        }
        this.category = category;
        if (category != null) {
            category.getBlogs().add(this);
        }
    }
    public static Blog createBlog(Long userId, BlogRequestDto.CreateBlog requestDto, Category category){
        Blog blog = Blog.builder()
                .userId(userId)
                .blogTitle(requestDto.getBlogTitle())
                .blogContents(requestDto.getBlogContents())
                .build();
        if (category != null) {
            blog.setCategory(category);
        }

        return blog;
    }

}
