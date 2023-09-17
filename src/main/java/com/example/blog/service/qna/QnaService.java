package com.example.blog.service.qna;

import com.example.blog.dto.qna.QnaRequestDto;
import com.example.blog.dto.qna.QnaResponseDto;

public interface QnaService {


    /**
     *  DESC : QnA 생성 요청을 처리한다.
     */
    QnaResponseDto.CreateBlog createQna(QnaRequestDto.CreateQna qnaCreateRequestDto);
}
