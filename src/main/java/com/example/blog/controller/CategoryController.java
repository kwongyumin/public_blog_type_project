package com.example.blog.controller;


import com.example.blog.common.codes.SuccessCode;
import com.example.blog.common.response.ApiResult;
import com.example.blog.dto.category.CategoryRequestDto;
import com.example.blog.dto.category.CategoryResponseDto;
import com.example.blog.service.category.CategoryService;
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

@Api(tags = {"CATEGORY API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * @param userId Long
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping(value = "/list/{userId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "카테고리 조회" , notes = "특정 유저의 블로그 카테고리 목록을 조회한다.", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = CategoryResponseDto.FindCategory.class)
    })
    public ResponseEntity<ApiResult> findCategoryList(@PathVariable(name = "userId") Long userId){
        CategoryResponseDto.FindCategory result  = categoryService.findCategoryList(userId);
        ApiResult data = new ApiResult(result, SuccessCode.SELECT_SUCCESS.getStatus(),SuccessCode.SELECT_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * @param categoryCreateRequestDto CategoryRequestDto.CreateCategory
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping(value = "/create" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "카테고리 생성" , notes = "카테고리 생성 요청을 처리한다.", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = CategoryResponseDto.CreateCategory.class)
    })
    public ResponseEntity<ApiResult> createCategory(@Valid @RequestBody CategoryRequestDto.CreateCategory categoryCreateRequestDto){
        CategoryResponseDto.CreateCategory result  = categoryService.createCategory(categoryCreateRequestDto);
        ApiResult data = new ApiResult(result, SuccessCode.INSERT_SUCCESS.getStatus(),SuccessCode.INSERT_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
