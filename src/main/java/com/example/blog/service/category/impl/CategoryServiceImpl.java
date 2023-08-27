package com.example.blog.service.category.impl;

import com.example.blog.common.codes.ErrorCode;
import com.example.blog.common.util.TokenUtils;
import com.example.blog.config.exception.BusinessExceptionHandler;
import com.example.blog.dto.category.CategoryRequestDto;
import com.example.blog.dto.category.CategoryResponseDto;
import com.example.blog.dto.user.UserDto;
import com.example.blog.model.category.Category;
import com.example.blog.repository.category.CategoryRepository;
import com.example.blog.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    /**
     * 해당 유저의 생성된 카테고리 목록을 반환한다.
     *
     * @param userId Long
     * @return CategoryResponseDto.FindCategory List
     */
    @Override
    public List<CategoryResponseDto.FindCategory> findCategoryList(Long userId) {
        return categoryRepository.findCategoryListByUserId(userId).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.SELECT_ERROR.getMessage() , ErrorCode.SELECT_ERROR));
    }

    /**
     * 카테고리 생성 요청을 처리한다.
     *
     * @param requestDto CategoryRequestDto.CreateCategory
     * @return CategoryResponseDto.CreateCategory
     */
    @Override
    public CategoryResponseDto.CreateCategory createCategory(CategoryRequestDto.CreateCategory requestDto) {

        // #1. 요청 유저의 정보를 토큰으로부터 조회
        UserDto user = TokenUtils.getUserFromAuthentication();
        // #2. 카테고리 객체 생성 및 초기화
        Category category = Category.createCategory(user.getUserId(), requestDto);
        // #3. 카테고리 정보 저장
        Category saveCategory = categoryRepository.save(category);
        if (saveCategory.getId() < 1){
            throw new BusinessExceptionHandler(ErrorCode.INSERT_ERROR.getMessage() , ErrorCode.INSERT_ERROR);
        }
        return new CategoryResponseDto.CreateCategory(saveCategory.getId(),user.getUserId());

    }
}
