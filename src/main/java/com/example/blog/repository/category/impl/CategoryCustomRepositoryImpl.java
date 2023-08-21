package com.example.blog.repository.category.impl;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl {

    private final JPAQueryFactory queryFactory;
}
