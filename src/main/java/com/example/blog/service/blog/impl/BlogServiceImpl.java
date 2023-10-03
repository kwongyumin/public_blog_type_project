package com.example.blog.service.blog.impl;


import com.example.blog.common.codes.ErrorCode;
import com.example.blog.common.util.TokenUtils;
import com.example.blog.config.exception.BusinessExceptionHandler;
import com.example.blog.dto.blog.BlogRequestDto;
import com.example.blog.dto.blog.BlogResponseDto;
import com.example.blog.dto.user.UserDto;
import com.example.blog.model.blog.Blog;
import com.example.blog.model.category.Category;
import com.example.blog.model.comments.Comments;
import com.example.blog.repository.blog.BlogRepository;
import com.example.blog.repository.category.CategoryRepository;
import com.example.blog.repository.comments.CommentsRepository;
import com.example.blog.service.blog.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final CategoryRepository categoryRepository;

    private final BlogRepository blogRepository;

    private final CommentsRepository commentsRepository;

    /**
     * 블로그의 상세 내용을 조회한다
     *
     * @param blogId Long
     * @return BlogResponseDto.FindBlog
     */
    @Override
    @Transactional(readOnly = true)
    public BlogResponseDto.FindBlogDetail findBlogDetail(Long blogId) {
        return blogRepository.findBlogDetailByBlogId(blogId).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.SELECT_ERROR.getMessage() , ErrorCode.SELECT_ERROR));
    }

    /**
     * 블로그 목록 조회 요청을 처리한다.
     *
     * @param categoryId Long
     * @return BlogResponseDto.FindBlog List
     */
    @Override
    @Transactional(readOnly = true)
    public List<BlogResponseDto.FindBlog> findBlogList(Long categoryId, int page, int size) {
        return blogRepository.findBlogListByCategoryId(categoryId,page,size).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.SELECT_ERROR.getMessage() , ErrorCode.SELECT_ERROR));
    }

    /**
     * 블로그 목록 조회 요청을 처리한다 (최신 업데이트 순 , 카테고리 무관).
     *
     * @param userId Long
     * @return BlogResponseDto.FindBlog List
     */
    @Override
    @Transactional(readOnly = true)
    public List<BlogResponseDto.FindBlog> findLatestBlogList(Long userId) {
        return blogRepository.findLatestBlogListByUserId(userId).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.SELECT_ERROR.getMessage() , ErrorCode.SELECT_ERROR));
    }

    /**
     * 블로그 생성 요청을 처리한다.
     *
     * @param requestDto BlogRequestDto.CreateBlog
     * @return BlogResponseDto.CreateBlog
     */
    @Override
    @Transactional
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
        if (saveBlog.getId() < 1){
            throw new BusinessExceptionHandler(ErrorCode.INSERT_ERROR.getMessage() , ErrorCode.INSERT_ERROR);
        }
        return new BlogResponseDto.CreateBlog(category.getId(),saveBlog.getId(), user.getUserId());
    }


    /**
     * 블로그 삭제 요청을 처리한다.
     *
     * @param blogId Long
     *  FIXME : 글 삭제에 대한 권한 검증을 인터셉터 or 필터 에서 처리하도록 생각할 것.
     */
    @Override
    public void deleteBlog(Long blogId) {

        // #1. api 접근 유저 정보 확인
        UserDto user = TokenUtils.getUserFromAuthentication();
        // #2.삭제 요청 블로그 조회
        Blog findBlog = blogRepository.findById(blogId).orElseThrow(
                () ->  new BusinessExceptionHandler(ErrorCode.SELECT_ERROR.getMessage() , ErrorCode.SELECT_ERROR));
        // #3. api 요청 유저의 pk 와 블로그 작성자의 pk가 동일하지 않은 경우 예외 처리
        if (! user.getUserId().equals(findBlog.getUserId())) {
            throw new BusinessExceptionHandler(ErrorCode.DELETE_ERROR.getMessage() , ErrorCode.DELETE_ERROR);
        }
        // FIXME: 블로그 및 댓글의 연관관계를 설정하여 삭제 처리 하도록 수정 필요
        // #4 블로그 하위 댓글도 동시 삭제 처리
        List<Comments> commentsList = commentsRepository.findCommentsListByBlog(findBlog).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.SELECT_ERROR.getMessage(), ErrorCode.SELECT_ERROR));
        // #5. 삭제
        blogRepository.delete(findBlog);
        commentsRepository.deleteAll(commentsList);
    }

}
