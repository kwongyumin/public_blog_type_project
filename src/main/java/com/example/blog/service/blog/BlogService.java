package com.example.blog.service.blog;

import com.example.blog.dto.blog.BlogRequestDto;
import com.example.blog.dto.blog.BlogResponseDto;

public interface BlogService {
    BlogResponseDto.CreateBlog createBlog(BlogRequestDto.CreateBlog requestDto);
}
