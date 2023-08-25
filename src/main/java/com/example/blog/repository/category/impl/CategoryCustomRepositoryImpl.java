package com.example.blog.repository.category.impl;


import com.example.blog.dto.category.CategoryResponseDto;
import com.example.blog.model.category.Category;
import com.example.blog.model.category.QCategory;
import com.example.blog.repository.category.CategoryCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<List<CategoryResponseDto.FindCategory>> findCategoryListByUserId(Long userId) {
        List<Category> categoryList = queryFactory
                .selectFrom(QCategory.category)
                .where(QCategory.category.userId.eq(userId))
                .fetch();



        return Optional.empty();
    }

    private List<CategoryResponseDto.FindCategory> entityToFindCategoryDtoList(List<Category> categoryList) {
        List<CategoryResponseDto.FindCategory> targetList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(categoryList)) {
            categoryList.stream().forEach(category -> {
                CategoryResponseDto.FindCategory target = CategoryResponseDto.FindCategory.builder()
                        .userId(category.getUserId())
                        .categoryId(category.getId())
                        .categoryName(category.getCategoryName())
                        .categoryColor(category.getColor())
                        .build();

            });
        }

        return targetList;
    }
}
