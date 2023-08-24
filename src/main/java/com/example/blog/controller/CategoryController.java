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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"CATEGORY API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;


    /**
     * @param categoryCreateRequestDto CategoryRequestDto.CreateCategory
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping(value = "/create" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "카테고리 생성" , notes = "카테고리 생성 요청을 처리한다.", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = CategoryResponseDto.CreateCategory.class)
    })
    public ResponseEntity<ApiResult> createBlog(@Valid @RequestBody CategoryRequestDto.CreateCategory categoryCreateRequestDto){
        CategoryResponseDto.CreateCategory result  = categoryService.createCategory(categoryCreateRequestDto);
        ApiResult data = new ApiResult(result, SuccessCode.JOIN_SUCCESS.getStatus(),SuccessCode.JOIN_SUCCESS.getMessage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
