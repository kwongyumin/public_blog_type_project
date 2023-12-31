package com.example.blog.dto.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(description = "카테고리 응답 파라미터 관리")
public class CategoryResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FindCategory implements Serializable {

        @ApiModelProperty(position = 1 , value ="카테고리 Idx",required = true)
        private Long categoryId;

        @ApiModelProperty(position = 2 , value ="유저 Idx",required = true)
        private Long userId;

        @ApiModelProperty(position = 3 , value ="카테고리 명",required = true)
        private String categoryName;

        @ApiModelProperty(position = 3 , value ="카테고리 컬러",required = true)
        private String categoryColor;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateCategory implements Serializable{

        @ApiModelProperty(position = 1 , value ="카테고리 Idx",required = true)
        private Long categoryId;

        @ApiModelProperty(position = 2 , value ="유저 Idx",required = true)
        private Long userId;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ModifyCategory implements Serializable {

        @ApiModelProperty(position = 1 , value ="카테고리 Idx",required = true)
        private Long categoryId;

        @ApiModelProperty(position = 2 , value ="유저 Idx",required = true)
        private Long userId;

    }


}

