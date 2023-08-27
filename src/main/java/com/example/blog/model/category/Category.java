package com.example.blog.model.category;

import com.example.blog.dto.category.CategoryRequestDto;
import com.example.blog.model.blog.Blog;
import com.example.blog.model.user.Authority;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private String categoryName;

    @Column
    private String categoryColor;

    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Blog> blogs = new ArrayList<>();

    @Column
    @LastModifiedDate // 수정 시간 자동 업데이트
    private LocalDateTime modTime;

    @Column(updatable = false)
    @CreatedDate // 등록 시간 자동 업데이트
    private LocalDateTime regTime;

    public void setBlogs(Blog blog) {
        this.blogs.add(blog);
        blogs.forEach(b -> b.setCategory(this));
    }

    public static Category createCategory(Long userId , CategoryRequestDto.CreateCategory requestDto) {
        return Category.builder()
                .userId(userId)
                .categoryName(requestDto.getCategoryName())
                .categoryColor(requestDto.getCategoryColor())
                .build();
    }



}
