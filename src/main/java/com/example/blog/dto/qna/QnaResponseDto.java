package com.example.blog.dto.qna;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(description = "QnA 응답 파라미터 관리")
public class QnaResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateBlog {

        @ApiModelProperty(position = 1 , value = "QnA idx",required = true, example = "")
        private Long qnaId;

        @ApiModelProperty(position = 2 , value = "유저 idx",required = true, example = "")
        private Long userId;

    }
}
