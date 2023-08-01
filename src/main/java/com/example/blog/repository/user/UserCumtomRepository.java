package com.example.blog.repository.user;


import com.example.blog.dto.user.UserResponseDto;

public interface UserCumtomRepository {

    UserResponseDto.LoginUser findLoginUserByEmail(String email);
}
