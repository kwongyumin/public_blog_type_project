package com.example.blog.service.qna.Impl;

import com.example.blog.common.codes.ErrorCode;
import com.example.blog.common.util.TokenUtils;
import com.example.blog.config.exception.BusinessExceptionHandler;
import com.example.blog.dto.qna.QnaRequestDto;
import com.example.blog.dto.qna.QnaResponseDto;
import com.example.blog.dto.user.UserDto;
import com.example.blog.model.qna.Qna;
import com.example.blog.repository.qna.QnaRepository;
import com.example.blog.service.qna.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {


    private final QnaRepository qnaRepository;

    /**
     * QnA 의 상세 내용을 조회한다.
     * @param qnaId Long
     * @return QnaResponseDto.FindFindQnaDetailQna
     */
    @Override
    @Transactional(readOnly = true)
    public QnaResponseDto.FindQnaDetail findQnaDetail(Long qnaId) {
        return qnaRepository.findQnaDetailByQnaId(qnaId).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.SELECT_ERROR.getMessage(),ErrorCode.SELECT_ERROR));
    }

    /**
     * QnA 목록 조회 요청을 처리한다.
     * @param page int
     * @param size int
     * @return QnaResponseDto.FindQna List
     */
    @Override
    @Transactional(readOnly = true)
    public List<QnaResponseDto.FindQna> findQnaList(int page, int size) {
        return qnaRepository.findAllQnaList(page,size).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.SELECT_ERROR.getMessage(), ErrorCode.SELECT_ERROR));
    }

    /**
     * QnA 목록 4개를 조회한다 (최신 업데이트 순)
     * @return QnaResponseDto.FindQna List
     */
    @Override
    @Transactional(readOnly = true)
    public List<QnaResponseDto.FindQna> findLatestQnaList() {
        // #1. 요청 유저의 정보를 토큰으로부터 조회
        UserDto user = TokenUtils.getUserFromAuthentication();
        return qnaRepository.findLatestQnaListByUserId(user.getUserId()).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.SELECT_ERROR.getMessage(), ErrorCode.SELECT_ERROR));
    }

    /**
     * QnA 생성 요청을 처리한다.
     *
     * @param requestDto QnaRequestDto.CreateQna
     * @return QnaResponseDto.CreateBlog
     */
    @Override
    @Transactional
    public QnaResponseDto.CreateBlog createQna(QnaRequestDto.CreateQna requestDto) {

        // #1. 요청 유저의 정보를 토큰으로부터 조회
        UserDto user = TokenUtils.getUserFromAuthentication();
        // #2. QnA 객체 생성 및 초기화
        Qna qna = Qna.createQna(user.getUserId(), requestDto);
        // #3. QnA 정보 저장
        Qna saveQna = qnaRepository.save(qna);
        if (saveQna.getId() < 1){
            throw new BusinessExceptionHandler(ErrorCode.INSERT_ERROR.getMessage() , ErrorCode.INSERT_ERROR);
        }
        return new QnaResponseDto.CreateBlog(saveQna.getId() , saveQna.getUserId());
    }
}
