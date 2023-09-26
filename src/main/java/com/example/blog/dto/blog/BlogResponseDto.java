package com.example.blog.dto.blog;

import com.example.blog.dto.comments.CommentsResponseDto;
import com.example.blog.dto.qna.QnaResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "블로그 요청 파라미터 관리")
public class BlogResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FindBlog implements Serializable{

        @ApiModelProperty(position = 1 ,  value = "유저 idx",required = true, example = "")
        private Long userId;

        @ApiModelProperty(position = 2 ,  value = "카테고리 idx",required = true, example = "")
        private Long categoryId;

        @ApiModelProperty(position = 3 ,  value = "블로그 idx",required = true, example = "")
        private Long blogId;

        @ApiModelProperty(position = 4 , value = "블로그 제목",required = true, example = "")
        private String blogTitle;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FindBlogDetail implements Serializable {

        @ApiModelProperty(position = 1 ,  value = "유저 idx",required = true, example = "")
        private Long userId;

        @ApiModelProperty(position = 2 ,  value = "블로그 idx",required = true, example = "")
        private Long blogId;

        @ApiModelProperty(position = 3, value = "블로그 제목",required = true, example = "")
        private String blogTitle;

        @ApiModelProperty(position = 4 , value = "블로그 내용",required = true, example = "")
        private String blogContents;

        @ApiModelProperty(position = 5 , value = "댓글 내용",required = true, example = "")
        private List<CommentsResponseDto.FindCommentsDetail> commentsList;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateBlog implements Serializable{

        @ApiModelProperty(position = 1 ,  value = "카테고리 idx",required = true, example = "")
        private Long categoryId;

        @ApiModelProperty(position = 2 , value = "블로그 idx",required = true, example = "")
        private Long blogId;

        @ApiModelProperty(position = 3 , value = "유저 idx",required = true, example = "")
        private Long userId;

    }

}
