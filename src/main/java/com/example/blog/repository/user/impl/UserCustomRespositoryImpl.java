package com.example.blog.repository.user.impl;

import com.example.blog.dto.user.UserResponseDto;
import com.example.blog.model.user.QUser;
import com.example.blog.model.user.User;
import com.example.blog.repository.user.UserCumtomRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserCustomRespositoryImpl implements UserCumtomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public UserResponseDto.LoginUser findLoginUserByEmail(String email) {
        // Querydsl을 사용하여 Product Entity를 조회합니다.
        User findUser = queryFactory
                .selectFrom(QUser.user)
                .where(QUser.user.email.eq(email))
                .fetchOne();

        // 조회한 User Entity를 UserResponseDto.LoginUser DTO로 매핑합니다.
        UserResponseDto.LoginUser result = new UserResponseDto.LoginUser().builder()
                .userId(findUser.getId())
                .build();

        return result;
    }
}
