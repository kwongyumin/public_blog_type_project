package com.example.blog.repository.qna;

import com.example.blog.dto.blog.BlogResponseDto;
import com.example.blog.dto.qna.QnaResponseDto;

import java.util.List;
import java.util.Optional;

public interface QnaCustomRepository {

    Optional<QnaResponseDto.FindQnaDetail> findQnaDetailByQnaId(Long qnaId);

    Optional<List<QnaResponseDto.FindQna>> findAllQnaList(int page, int size);

    Optional<List<QnaResponseDto.FindQna>> findLatestQnaListByUserId(Long userId);
}
