package com.example.blog.controller;

import com.example.blog.common.codes.SuccessCode;
import com.example.blog.common.response.ApiResult;
import com.example.blog.dto.blog.BlogRequestDto;
import com.example.blog.dto.blog.BlogResponseDto;
import com.example.blog.dto.qna.QnaRequestDto;
import com.example.blog.dto.qna.QnaResponseDto;
import com.example.blog.service.qna.QnaService;
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

@Api(tags = {"QNA API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnaController {


    private final QnaService qnaService;

    /**
     * @param qnaCreateRequestDto QnaRequestDto.CreateQna
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping(value = "/create" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "QnA 생성" , notes = "QnA 생성 요청을 처리한다.", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = BlogResponseDto.CreateBlog.class)
    })
    public ResponseEntity<ApiResult> createQna(@Valid @RequestBody QnaRequestDto.CreateQna qnaCreateRequestDto){

        QnaResponseDto.CreateBlog result = qnaService.createQna(qnaCreateRequestDto);
        ApiResult data = new ApiResult(null, SuccessCode.INSERT_SUCCESS.getStatus(),SuccessCode.INSERT_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
