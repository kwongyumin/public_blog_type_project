package com.example.blog.controller;

import com.example.blog.common.codes.SuccessCode;
import com.example.blog.common.constants.AuthConstants;
import com.example.blog.common.response.ApiResult;
import com.example.blog.dto.auth.AuthRequestDto;
import com.example.blog.dto.auth.AuthResponseDto;
import com.example.blog.dto.user.UserResponseDto;
import com.example.blog.service.auth.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"AUTH API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * @param userAuthRequestDto AuthRequestDto.GenerateUserToken
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping(value = "/generate-token" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "토큰 발급 요청 (회원 로그인)" , notes = "서비스 회원가입 처리된 유저에 한해서 토큰 발급 요청을 처리한다.", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = AuthResponseDto.GenerateUserToken.class)
    })
    public ResponseEntity<ApiResult> generateToken(@Valid @RequestBody AuthRequestDto.GenerateUserToken userAuthRequestDto){
        AuthResponseDto.GenerateUserToken result  = authService.generateToken(userAuthRequestDto, AuthConstants.ROLE_USER);
        ApiResult data = new ApiResult(result, SuccessCode.TOKEN_ISSUED_SUCCESS.getStatus(),SuccessCode.TOKEN_ISSUED_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
