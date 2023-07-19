package com.example.blog.controller;

import com.example.blog.common.codes.SuccessCode;
import com.example.blog.common.response.ApiResult;
import com.example.blog.dto.user.UserRequestDto;
import com.example.blog.dto.user.UserResponseDto;
import com.example.blog.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"USER API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * Desc : 유저 로그인 처리 (일반 유저 대상)
     * @param loginUserRequestDto
     * @return
     */
    @PostMapping(value = "/login" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "일반 회원 로그인 처리" , notes = "일반 회원의 로그인 요청을 처리한다", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserResponseDto.LoginUser.class)
    })
    public ApiResult loginUser(@RequestBody UserRequestDto.LoginUser loginUserRequestDto) {
        return null;
    }

    /**
     * Desc : 회원가입 요청 처리 (일반 유저 대상)
     * @param userJoinRequestDto
     * @return
     */
    @PostMapping(value = "/join" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "일반 회원가입 처리 요청" , notes = "서비스 내에서 자체적으로 회원가입 요청을 처리한다.", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserResponseDto.JoinUser.class)
    })
    public ApiResult saveUser(@RequestBody UserRequestDto.JoinUser userJoinRequestDto){
        UserResponseDto.JoinUser result  = userService.saveUser(userJoinRequestDto);
        // FIXME : 해당 생성자를 더 간결하게 처리하는 방법에 대하여 생각해보자.
        return new ApiResult(result, SuccessCode.INSERT_SUCCESS.getStatus(),SuccessCode.INSERT_SUCCESS.getMessage());
    }

}
