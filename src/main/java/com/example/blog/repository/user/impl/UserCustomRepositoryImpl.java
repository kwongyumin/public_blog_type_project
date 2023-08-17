package com.example.blog.repository.user.impl;

import com.example.blog.dto.user.UserDto;
import com.example.blog.model.user.Authority;
import com.example.blog.model.user.QUser;
import com.example.blog.model.user.User;
import com.example.blog.repository.user.UserCumtomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCumtomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<UserDto> findUserByEmail(String email) {
        User user = queryFactory
                .selectFrom(QUser.user)
                .where(QUser.user.email.eq(email))
                .fetchOne();

        return Optional.ofNullable(user != null ? mapToUserVo(user) : null);
    }

    private UserDto mapToUserVo(User user) {
        List<String> authorityList = user.getRoles().stream()
                .map(Authority::getAuthorityName)
                .collect(Collectors.toList());

        return UserDto.builder()
                .userId(user.getId())
                .userName(user.getUserName())
                .userEmail(user.getEmail())
                .authorityList(authorityList)
                .build();
    }
}
