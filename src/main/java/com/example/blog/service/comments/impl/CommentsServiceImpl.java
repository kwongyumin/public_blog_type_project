package com.example.blog.service.comments.impl;

import com.example.blog.common.codes.ErrorCode;
import com.example.blog.common.util.TokenUtils;
import com.example.blog.config.exception.BusinessExceptionHandler;
import com.example.blog.dto.comments.CommentsRequestDto;
import com.example.blog.dto.comments.CommentsResponseDto;
import com.example.blog.dto.user.UserDto;
import com.example.blog.model.comments.Comments;
import com.example.blog.model.comments.WriteType;
import com.example.blog.repository.comments.CommentsRepository;
import com.example.blog.service.comments.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {


    private final CommentsRepository commentsRepository;

    /**
     * 댓글 생성 요청을 처리한다.
     *
     * @param requestDto CommentsRequestDto.CreateComments
     * @return CommentsResponseDto.CreateComments
     */
    @Override
    @Transactional
    public CommentsResponseDto.CreateComments createComments(CommentsRequestDto.CreateComments requestDto) {

        // #1. 요청 유저의 정보를 토큰으로부터 조회
        UserDto user = TokenUtils.getUserFromAuthentication();
        // #2. 댓글 객체 생성 및 초기화
        Comments comments = Comments.createComments(user.getUserId(), requestDto);
        // #3. 댓글 정보 저장
        Comments saveComments = commentsRepository.save(comments);
        if (comments.getId() < 1) {
            throw new BusinessExceptionHandler(ErrorCode.INSERT_ERROR.getMessage(), ErrorCode.INSERT_ERROR);
        }
        return new CommentsResponseDto.CreateComments(saveComments.getId(), saveComments.getWriteTypeId(), saveComments.getUserId());
    }

}