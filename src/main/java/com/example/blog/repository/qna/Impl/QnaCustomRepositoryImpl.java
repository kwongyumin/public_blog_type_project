package com.example.blog.repository.qna.Impl;

import com.example.blog.repository.qna.QnaCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QnaCustomRepositoryImpl implements QnaCustomRepository {

    private final JPAQueryFactory queryFactory;
}
