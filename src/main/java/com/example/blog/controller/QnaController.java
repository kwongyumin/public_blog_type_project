package com.example.blog.controller;

import com.example.blog.common.codes.SuccessCode;
import com.example.blog.common.response.ApiResult;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"QNA API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnaController {


    private final QnaService qnaService;

    /**
     * @param qnaId Long
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping(value = "/list-detail/{qnaId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "QnA 상세 조회" , notes = "선택 QnA 의 상세 내용을 조회한다.", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = QnaResponseDto.FindQnaDetail.class)
    })
    public ResponseEntity<ApiResult> findQnaDetail(@PathVariable(name = "qnaId") Long qnaId){
        QnaResponseDto.FindQnaDetail result = qnaService.findQnaDetail(qnaId);
        ApiResult data = new ApiResult(result, SuccessCode.SELECT_SUCCESS.getStatus(),SuccessCode.SELECT_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * @param page int
     * @param size int
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping(value = "/list" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "QnA 목록 조회 (페이징)" , notes = "QnA 전체 목록을 조회한다.", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = QnaResponseDto.FindQna.class)
    })
    public ResponseEntity<ApiResult> findQnaList(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "10") int size){
        List<QnaResponseDto.FindQna> resultList = qnaService.findQnaList(page,size);
        ApiResult data = new ApiResult(resultList, SuccessCode.SELECT_SUCCESS.getStatus(),SuccessCode.SELECT_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping(value = "/latest-list" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "QnA 목록 조회 (최신 업데이트 순)" , notes = "QnA 최신 업데이트 순 4개를 조회한다.", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = QnaResponseDto.FindQna.class)
    })
    public ResponseEntity<ApiResult> findLatestQnaList(){
        List<QnaResponseDto.FindQna> resultList = qnaService.findLatestQnaList();
        ApiResult data = new ApiResult(resultList, SuccessCode.SELECT_SUCCESS.getStatus(),SuccessCode.SELECT_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * @param qnaCreateRequestDto QnaRequestDto.CreateQna
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping(value = "/create" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "QnA 생성" , notes = "QnA 생성 요청을 처리한다.", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = QnaResponseDto.CreateBlog.class)
    })
    public ResponseEntity<ApiResult> createQna(@Valid @RequestBody QnaRequestDto.CreateQna qnaCreateRequestDto){

        QnaResponseDto.CreateBlog result = qnaService.createQna(qnaCreateRequestDto);
        ApiResult data = new ApiResult(result, SuccessCode.INSERT_SUCCESS.getStatus(),SuccessCode.INSERT_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
