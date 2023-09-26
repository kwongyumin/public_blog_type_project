package com.example.blog.service.comments;

import com.example.blog.dto.comments.CommentsRequestDto;
import com.example.blog.dto.comments.CommentsResponseDto;

public interface CommentsService {

    /**
     *  DESC : 작성타입(블로그 및 QnA)의 댓글 내용을 조회한다
     */
    CommentsResponseDto.CreateComments createComments(CommentsRequestDto.CreateComments commentsCreateRequestDto);
}
