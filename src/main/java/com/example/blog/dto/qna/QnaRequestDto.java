package com.example.blog.dto.qna;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;


@ApiModel(description = "QnA 요청 파라미터 관리")
public class QnaRequestDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateQna {

        @ApiModelProperty(position = 1 , value = "QnA 제목",required = true, example = "")
        @NotEmpty(message = "QnA 제목은 필수항목입니다.")
        private String qnaTitle;

        @ApiModelProperty(position = 2 , value = "QnA 내용",required = true, example = "")
        @NotEmpty(message = "QnA 글 내용은 필수항목입니다.")
        private String qnaContents;
    }
}
