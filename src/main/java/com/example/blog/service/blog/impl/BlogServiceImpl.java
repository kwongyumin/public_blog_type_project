package com.example.blog.service.blog.impl;


import com.example.blog.dto.blog.BlogRequestDto;
import com.example.blog.dto.blog.BlogResponseDto;
import com.example.blog.service.blog.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {


    @Override
    public BlogResponseDto.createBlog createBlog(BlogRequestDto.createBlog requestDto) {
        return null;
    }
}
