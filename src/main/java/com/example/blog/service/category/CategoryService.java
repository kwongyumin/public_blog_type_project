package com.example.blog.service.category;

import com.example.blog.dto.category.CategoryRequestDto;
import com.example.blog.dto.category.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    /**
     *  DESC : 특정 유저의 블로그 카테고리 목록을 조회한다.
     */
    List<CategoryResponseDto.FindCategory> findCategoryList(Long userId);

    /**
     *  DESC : 카테고리 생성 요청을 처리한다.
     */
    CategoryResponseDto.CreateCategory createCategory(CategoryRequestDto.CreateCategory categoryCreateRequestDto);


}
