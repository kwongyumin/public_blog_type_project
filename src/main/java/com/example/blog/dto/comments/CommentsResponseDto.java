package com.example.blog.dto.comments;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(description = "댓글 응답 파라미터 관리")
public class CommentsResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateComments implements Serializable {

        @ApiModelProperty(position = 1 ,  value = "댓글 idx",required = true, example = "")
        private Long commentsId;

        @ApiModelProperty(position = 2 , value = "블로그 idx",required = true, example = "")
        private Long blogId;

        @ApiModelProperty(position = 3 , value = "유저 idx",required = true, example = "")
        private Long userId;

    }
}
