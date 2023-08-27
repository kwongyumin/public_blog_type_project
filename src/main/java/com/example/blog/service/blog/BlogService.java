package com.example.blog.service.blog;

import com.example.blog.dto.blog.BlogRequestDto;
import com.example.blog.dto.blog.BlogResponseDto;

import java.util.List;

public interface BlogService {

    /**
     *  DESC : 블로그의 상세 내용을 조회한다
     */
    BlogResponseDto.FindBlogDetail findBlogDetail(Long blogId);

    /**
     *  DESC : 특정 카테고리 하위의 블로그 목록을 조회한다.
     */
    List<BlogResponseDto.FindBlog> findBlogList(Long categoryId, int page, int size);

    /**
     *  DESC :   선택 블로그 유저의 최신 업데이트 블로그 4개를 조회한다.
     */
    List<BlogResponseDto.FindBlog> findLatestBlogList(Long userId);

    /**
     *  DESC : 블로그 생성 요청을 처리한다.
     */
    BlogResponseDto.CreateBlog createBlog(BlogRequestDto.CreateBlog requestDto);

    /**
     *  DESC : 블로그 삭제 요청을 처리한다.
     */
    void deleteBlog(Long blogId);
}
