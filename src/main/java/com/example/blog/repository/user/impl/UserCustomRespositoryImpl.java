package com.example.blog.repository.user.impl;

import com.example.blog.repository.user.UserCumtomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserCustomRespositoryImpl implements UserCumtomRepository {

    private final JPAQueryFactory queryFactory;


}
