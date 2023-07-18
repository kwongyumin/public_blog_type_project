package com.example.blog.controller;

import com.example.blog.common.codes.SuccessCode;
import com.example.blog.common.response.ApiResponse;
import com.example.blog.dto.user.UserRequestDto;
import com.example.blog.dto.user.UserResponseDto;
import com.example.blog.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ApiResponse saveUser(@RequestBody UserRequestDto.JoinUser userJoinRequestDto){
        UserResponseDto.JoinUser result  = userService.saveUser(userJoinRequestDto);
        // FIXME : 해당 생성자를 더 간결하게 처리하는 방법에 대하여 생각해보자.
        return new ApiResponse(result, SuccessCode.INSERT_SUCCESS.getStatus(),SuccessCode.INSERT_SUCCESS.getMessage());
    }

}
