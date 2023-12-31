package com.example.blog.repository.user;


import com.example.blog.dto.user.UserDto;

import java.util.Optional;

public interface UserCustomRepository {

    Optional<UserDto> findUserByEmail(String email);
}
