package com.example.blog.repository.user.impl;

import com.example.blog.dto.user.UserDto;
import com.example.blog.model.user.Authority;
import com.example.blog.model.user.QUser;
import com.example.blog.model.user.User;
import com.example.blog.repository.user.UserCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<UserDto> findUserByEmail(String email) {
        User user = queryFactory
                .selectFrom(QUser.user)
                .where(QUser.user.email.eq(email))
                .fetchOne();

        return Optional.ofNullable(user != null ? entityToUserDto(user) : null);
    }


    /*******************************************************************************************************************
     * DESC : entity -> dto transferMethod
     *******************************************************************************************************************/

    private UserDto entityToUserDto(User user) {
        List<String> authorityList = user.getRoles().stream()
                .map(Authority::getAuthorityName)
                .collect(Collectors.toList());

        return UserDto.builder()
                .userId(user.getId())
                .userName(user.getUserName())
                .userEmail(user.getEmail())
                .userPassword(user.getPassword())
                .authorityList(authorityList)
                .build();
    }
}
