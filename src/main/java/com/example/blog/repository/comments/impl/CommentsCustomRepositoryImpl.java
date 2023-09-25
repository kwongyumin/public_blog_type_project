package com.example.blog.repository.comments.impl;

import com.example.blog.repository.comments.CommentsCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentsCustomRepositoryImpl implements CommentsCustomRepository {

    private final JPAQueryFactory queryFactory;
}
