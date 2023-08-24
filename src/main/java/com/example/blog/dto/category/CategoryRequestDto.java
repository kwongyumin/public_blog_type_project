package com.example.blog.dto.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@ApiModel(description = "카테고리 요청 파라미터 관리")
public class CategoryRequestDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateCategory {

        @ApiModelProperty(position = 1 , value ="카테고리 명",required = true)
        @NotNull(message = "카테고리 명칭은 필수항목입니다.")
        private String categoryName;

        @ApiModelProperty(position = 2 , value ="카테고리 컬러",required = true)
        @NotNull(message = "카테고리 컬러는 필수항목입니다.")
        private String categoryColor;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ModifyCategory {

        @ApiModelProperty(position = 1 , value ="카테고리 명",required = true)
        @NotNull(message = "카테고리 명칭은 필수항목입니다.")
        private String categoryName;

        @ApiModelProperty(position = 2 , value ="카테고리 컬러",required = true)
        @NotNull(message = "카테고리 컬러는 필수항목입니다.")
        private String categoryColor;

    }
}
