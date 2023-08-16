package com.example.blog.dto.blog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(description = "블로그 요청 파라미터 관리")
public class BlogRequestDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class createBlog {

        @ApiModelProperty(position = 1 ,  value = "카테고리 idx",required = true, example = "")
        private Long categoryId;

        @ApiModelProperty(position = 2 , value = "블로그 제목",required = true, example = "")
        private String blogTitle;

        @ApiModelProperty(position = 3 , value = "블로그 내용",required = true, example = "")
        private String blogContents;
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ModifyBlog {

        @ApiModelProperty(position = 1 , value = "블로그 제목",required = true, example = "")
        private String blogTitle;

        @ApiModelProperty(position = 2 , value = "블로그 내용",required = true, example = "")
        private String blogContents;
    }
}