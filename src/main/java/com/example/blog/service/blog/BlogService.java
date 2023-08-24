package com.example.blog.service.blog;

import com.example.blog.dto.blog.BlogRequestDto;
import com.example.blog.dto.blog.BlogResponseDto;

public interface BlogService {

    /**
     *  DESC : 블로그 생성 요청을 처리한다.
     */
    BlogResponseDto.CreateBlog createBlog(BlogRequestDto.CreateBlog requestDto);
}
