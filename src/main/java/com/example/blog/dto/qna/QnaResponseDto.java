package com.example.blog.dto.qna;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(description = "QnA 응답 파라미터 관리")
public class QnaResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FindQna {

        @ApiModelProperty(position = 1 ,  value = "유저 idx",required = true, example = "")
        private Long userId;

        @ApiModelProperty(position = 2 ,  value = "QnA idx",required = true, example = "")
        private Long qnaId;

        @ApiModelProperty(position = 3 , value = "QnA 제목",required = true, example = "")
        private String qnaTitle;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FindQnaDetail implements Serializable {

        @ApiModelProperty(position = 1 ,  value = "유저 idx",required = true, example = "")
        private Long userId;

        @ApiModelProperty(position = 2 ,  value = "QnA idx",required = true, example = "")
        private Long qnaId;

        @ApiModelProperty(position = 3, value = "QnAv 제목",required = true, example = "")
        private String qnaTitle;

        @ApiModelProperty(position = 4 , value = "QnA 내용",required = true, example = "")
        private String qnaContents;

    }

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
