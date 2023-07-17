package com.example.blog.controller;

import com.example.blog.common.response.ApiResponse;
import com.example.blog.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @PostMapping("/join")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody UserRequestDto.JoinUser userJoinRequestDto) throws Exception {

        return null;
    }

}
