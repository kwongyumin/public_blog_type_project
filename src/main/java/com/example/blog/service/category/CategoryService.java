package com.example.blog.service.category;

import com.example.blog.dto.category.CategoryRequestDto;
import com.example.blog.dto.category.CategoryResponseDto;

public interface CategoryService {

    /**
     *  DESC : 카테고리 생성 요청을 처리한다.
     */
    CategoryResponseDto.CreateCategory createCategory(CategoryRequestDto.CreateCategory categoryCreateRequestDto);
}
