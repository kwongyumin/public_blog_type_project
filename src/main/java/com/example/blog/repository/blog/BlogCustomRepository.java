package com.example.blog.repository.blog;

import com.example.blog.dto.blog.BlogResponseDto;

import java.util.List;
import java.util.Optional;

public interface BlogCustomRepository {


    Optional<BlogResponseDto.FindBlogDetail> findBlogDetailByBlogId(Long blogId);

    Optional<List<BlogResponseDto.FindBlog>> findBlogListByCategoryId(Long categoryId, int page, int size);
}
