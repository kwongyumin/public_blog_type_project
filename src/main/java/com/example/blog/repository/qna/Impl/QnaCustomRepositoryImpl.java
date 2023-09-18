package com.example.blog.repository.qna.Impl;

import com.example.blog.dto.qna.QnaResponseDto;
import com.example.blog.model.qna.QQna;
import com.example.blog.model.qna.Qna;
import com.example.blog.repository.qna.QnaCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QnaCustomRepositoryImpl implements QnaCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<QnaResponseDto.FindQnaDetail> findQnaDetailByQnaId(Long qnaId) {
        Qna qna = queryFactory
                .selectFrom(QQna.qna)
                .where(QQna.qna.id.eq(qnaId))
                .fetchOne();

        return Optional.ofNullable(qna != null ? entityToFindQnaDetailDto(qna) : null);
    }

    @Override
    public Optional<List<QnaResponseDto.FindQna>> findAllQnaList(int page, int size) {
        List<Qna> qnaList = queryFactory
                .selectFrom(QQna.qna)
                .orderBy(QQna.qna.modTime.desc())
                .offset(page * size)
                .limit(size)
                .fetch();

        return Optional.ofNullable(!CollectionUtils.isEmpty(qnaList) ? entityToFindQnaDtoList(qnaList) : null);

    }

    @Override
    public Optional<List<QnaResponseDto.FindQna>> findLatestQnaListByUserId(Long userId) {

        List<Qna> latestQnaList = queryFactory
                .selectFrom(QQna.qna)
                .where(QQna.qna.userId.eq(userId))
                .orderBy(QQna.qna.modTime.desc())
                .limit(4)
                .fetch();

        return Optional.ofNullable(!CollectionUtils.isEmpty(latestQnaList) ? entityToFindQnaDtoList(latestQnaList) : null);

    }

    /*******************************************************************************************************************
     * DESC : entity -> dto transferMethod
     *******************************************************************************************************************/

    private QnaResponseDto.FindQnaDetail entityToFindQnaDetailDto(Qna qna) {
        return QnaResponseDto.FindQnaDetail.builder()
                .qnaId(qna.getId())
                .userId(qna.getUserId())
                .qnaTitle(qna.getQnaTitle())
                .qnaContents(qna.getQnaContents())
                .build();
    }

    private List<QnaResponseDto.FindQna> entityToFindQnaDtoList(List<Qna> qnaList) {
        List<QnaResponseDto.FindQna> targetList = new ArrayList<>();
        qnaList.forEach(qna -> {
            QnaResponseDto.FindQna target = QnaResponseDto.FindQna.builder()
                    .userId(qna.getUserId())
                    .qnaId(qna.getId())
                    .qnaTitle(qna.getQnaTitle())
                    .build();
            targetList.add(target);
        });
        return targetList;
    }



}
