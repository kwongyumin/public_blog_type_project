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
    public static class FindCommentsDetail implements Serializable {

        @ApiModelProperty(position = 1 , value = "댓글 idx",required = true, example = "")
        private Long commentsId;

        @ApiModelProperty(position = 2 , value = "블로그 , QnA idx",required = true, example = "")
        private Long writeTypeId;

        @ApiModelProperty(position = 3 , value = "유저 idx",required = true, example = "")
        private Long userId;

        @ApiModelProperty(position = 4 , value = "댓글 내용",required = true, example = "")
        private String contents;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateComments implements Serializable {

        @ApiModelProperty(position = 1 ,  value = "댓글 idx",required = true, example = "")
        private Long commentsId;

        @ApiModelProperty(position = 2 , value = "블로그 , QnA idx",required = true, example = "")
        private Long writeTypeId;

        @ApiModelProperty(position = 3 , value = "유저 idx",required = true, example = "")
        private Long userId;

    }


}
