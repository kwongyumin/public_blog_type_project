package com.example.blog.controller;

import com.example.blog.common.codes.SuccessCode;
import com.example.blog.common.response.ApiResult;
import com.example.blog.dto.blog.BlogRequestDto;
import com.example.blog.dto.blog.BlogResponseDto;
import com.example.blog.service.blog.BlogService;
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

@Api(tags = {"BLOG API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    /**
     * @param blogId Long
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping(value = "/list-detail/{blogId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "블로그 상세 조회" , notes = "블로그의 상세 내용을 조회한다", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = BlogResponseDto.FindBlogDetail.class)
    })
    public ResponseEntity<ApiResult> findBlogDetail(@PathVariable(name = "blogId") Long blogId){
        BlogResponseDto.FindBlogDetail result  = blogService.findBlogDetail(blogId);
        ApiResult data = new ApiResult(result, SuccessCode.SELECT_SUCCESS.getStatus(),SuccessCode.SELECT_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * @param categoryId Long
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping(value = "/list/{categoryId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "블로그 목록 조회 (페이징)" , notes = "특정 카테고리 하위의 블로그 목록을 조회한다.", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = BlogResponseDto.FindBlog.class)
    })
    public ResponseEntity<ApiResult> findBlogList(@PathVariable(name = "categoryId") Long categoryId ,
                                              @RequestParam(defaultValue = "0") int page ,
                                              @RequestParam(defaultValue = "10") int size) {
        List<BlogResponseDto.FindBlog> resultList  = blogService.findBlogList(categoryId,page,size);
        ApiResult data = new ApiResult(resultList, SuccessCode.SELECT_SUCCESS.getStatus(),SuccessCode.SELECT_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * @param userId Long
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping(value = "/latest-list/{userId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "블로그 목록 조회 (최신 업데이트 순, 카테고리 무관)" , notes = "선택 블로그 유저의 최신 업데이트 블로그 4개를 조회한다.", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = BlogResponseDto.FindBlog.class)
    })
    public ResponseEntity<ApiResult> findLatestBlogList(@PathVariable(name = "userId") Long userId) {
        List<BlogResponseDto.FindBlog> resultList  = blogService.findLatestBlogList(userId);
        ApiResult data = new ApiResult(resultList, SuccessCode.SELECT_SUCCESS.getStatus(),SuccessCode.SELECT_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * @param blogCreateRequestDto BlogRequestDto.createBlog
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping(value = "/create" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "블로그 생성" , notes = "블로그 생성 요청을 처리한다.", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = BlogResponseDto.CreateBlog.class)
    })
    public ResponseEntity<ApiResult> createBlog(@Valid @RequestBody BlogRequestDto.CreateBlog blogCreateRequestDto){
        BlogResponseDto.CreateBlog result  = blogService.createBlog(blogCreateRequestDto);
        ApiResult data = new ApiResult(result, SuccessCode.INSERT_SUCCESS.getStatus(),SuccessCode.INSERT_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * @param blogId Long
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @DeleteMapping(value = "/delete/{blogId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "블로그 삭제" , notes = "블로그 삭제 요청을 처리한다.", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = ApiResult.class)
    })
    public ResponseEntity<ApiResult> deleteBlog(@PathVariable(name = "blogId") Long blogId){
        blogService.deleteBlog(blogId);
        ApiResult data = new ApiResult(null, SuccessCode.DELETE_SUCCESS.getStatus(),SuccessCode.DELETE_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }




}
