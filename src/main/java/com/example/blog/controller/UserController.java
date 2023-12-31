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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"USER API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    /**
     * @param userJoinRequestDto UserResponseDto.JoinUser
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping(value = "/join" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "일반 회원가입 요청" , notes = "서비스 내에서 자체적으로 회원가입 요청을 처리한다.", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = UserResponseDto.JoinUser.class)
    })
    public ResponseEntity<ApiResult> saveUser(@Valid @RequestBody UserRequestDto.JoinUser userJoinRequestDto){
        UserResponseDto.JoinUser result  = userService.saveUser(userJoinRequestDto);
        ApiResult data = new ApiResult(result,SuccessCode.JOIN_SUCCESS.getStatus(),SuccessCode.JOIN_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
