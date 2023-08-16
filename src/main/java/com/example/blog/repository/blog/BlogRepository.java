package com.example.blog.repository.blog;

import com.example.blog.model.blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Long> , BlogCustomRepository {
}
