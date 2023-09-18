package com.example.blog.service.qna;

import com.example.blog.dto.qna.QnaRequestDto;
import com.example.blog.dto.qna.QnaResponseDto;

import java.util.List;

public interface QnaService {


    /**
     *  DESC : QnA 상세 내용을 조회한다.
     */
    QnaResponseDto.FindQnaDetail findQnaDetail(Long qnaId);

    /**
     *  DESC : QnA 전체 목록을 조회한다.
     */
    List<QnaResponseDto.FindQna> findQnaList(int page, int size);

    /**
     *  DESC : QnA 목록 4개를 조회한다 (최신 업데이트 순)
     */
    List<QnaResponseDto.FindQna> findLatestQnaList();

    /**
     *  DESC : QnA 생성 요청을 처리한다.
     */
    QnaResponseDto.CreateBlog createQna(QnaRequestDto.CreateQna qnaCreateRequestDto);


}
