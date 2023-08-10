package com.example.blog.controller;

import com.example.blog.common.response.ApiResult;
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
     * @param
     * @return
     */
    @PostMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "" , notes = "", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success")
    })
    public ResponseEntity<ApiResult> generateToken(){
        ApiResult data = null;
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
