package com.example.blog.repository.category;

import com.example.blog.dto.category.CategoryResponseDto;

import java.util.List;
import java.util.Optional;

public interface CategoryCustomRepository {

    Optional<List<CategoryResponseDto.FindCategory>> findCategoryListByUserId(Long userId);
}
