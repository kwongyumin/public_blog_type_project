package com.example.blog.repository.blog.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BlogCustomRepositoryImpl {

    private final JPAQueryFactory queryFactory;
}
