package com.example.blog.service.blog.impl;


import com.example.blog.common.util.TokenUtils;
import com.example.blog.dto.blog.BlogRequestDto;
import com.example.blog.dto.blog.BlogResponseDto;
import com.example.blog.dto.user.UserDto;
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

        // #1. 요청 유저의 정보를 토큰으로부터 조회
        UserDto user = TokenUtils.getUserFromAuthentication();



        return null;
    }
}
