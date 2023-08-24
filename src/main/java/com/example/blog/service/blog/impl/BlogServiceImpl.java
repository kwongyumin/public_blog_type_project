package com.example.blog.service.blog.impl;


import com.example.blog.common.codes.ErrorCode;
import com.example.blog.common.util.TokenUtils;
import com.example.blog.config.exception.BusinessExceptionHandler;
import com.example.blog.dto.blog.BlogRequestDto;
import com.example.blog.dto.blog.BlogResponseDto;
import com.example.blog.dto.user.UserDto;
import com.example.blog.model.blog.Blog;
import com.example.blog.model.category.Category;
import com.example.blog.repository.blog.BlogRepository;
import com.example.blog.repository.category.CategoryRepository;
import com.example.blog.service.blog.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final CategoryRepository categoryRepository;

    private final BlogRepository blogRepository;


    /**
     * 블로그 생성 요청을 처리한다.
     *
     * @param requestDto BlogRequestDto.CreateBlog
     * @return BlogResponseDto.CreateBlog
     */
    @Override
    public BlogResponseDto.CreateBlog createBlog(BlogRequestDto.CreateBlog requestDto) {

        // #1. 요청 유저의 정보를 토큰으로부터 조회
        UserDto user = TokenUtils.getUserFromAuthentication();
        // #2. 전달받은 카테고리 pk -> 카테고리 정보 조회
        Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.SELECT_ERROR.getMessage() , ErrorCode.SELECT_ERROR));
        // #3. 블로그 객체 생성 및 초기화
        Blog blog = Blog.createBlog(user.getUserId(), requestDto, category);
        // #4. 블로그 정보 저장
        Blog saveBlog = blogRepository.save(blog);
        if (saveBlog == null){
            throw new BusinessExceptionHandler(ErrorCode.INSERT_ERROR.getMessage() , ErrorCode.INSERT_ERROR);
        }
        return new BlogResponseDto.CreateBlog(category.getId(),saveBlog.getId(), user.getUserId());
    }
}
