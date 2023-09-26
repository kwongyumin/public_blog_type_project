package com.example.blog.controller;

import com.example.blog.common.codes.SuccessCode;
import com.example.blog.common.response.ApiResult;
import com.example.blog.dto.blog.BlogResponseDto;
import com.example.blog.dto.comments.CommentsRequestDto;
import com.example.blog.dto.comments.CommentsResponseDto;
import com.example.blog.service.comments.CommentsService;
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

@Api(tags = {"COMMENTS API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentsController {


    private final CommentsService commentsService;

    /**
     * @param
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping(value = "/create" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "댓글 생성" , notes = "블로그 및 QnA 댓글 생성 요청을 처리한다.", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = CommentsResponseDto.CreateComments.class)
    })
    public ResponseEntity<ApiResult> createComments(@Valid @RequestBody CommentsRequestDto.CreateComments commentsCreateRequestDto){
        CommentsResponseDto.CreateComments result = commentsService.createComments(commentsCreateRequestDto);
        ApiResult data = new ApiResult(result, SuccessCode.INSERT_SUCCESS.getStatus(),SuccessCode.INSERT_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


}
