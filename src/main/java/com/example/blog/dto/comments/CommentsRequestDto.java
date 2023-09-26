package com.example.blog.dto.comments;

import com.example.blog.model.comments.WriteType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "댓글 요청 파라미터 관리")
public class CommentsRequestDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateComments {

        @ApiModelProperty(position = 1 , value ="작성 타입",required = true)
        @NotEmpty(message = "블로그 및 QnA 의 pk는 필수항목입니다.")
        private Long writeTypeId;

        @ApiModelProperty(position = 2 , value ="0: 블로그 , 1: QnA",required = true)
        @NotEmpty(message = "블로그 및 QnA 작성 타입은 필수항목입니다.")
        private int writeTypeNumber;

        @ApiModelProperty(position = 3 , value ="댓글 내용",required = true)
        @NotEmpty(message = "댓글 내용은 필수항목입니다.")
        private String contents;

        @ApiModelProperty(position = 4 ,value = "블로그 , QnA 타입" ,required = false)
        @JsonIgnore
        private WriteType writeType;

        public WriteType getWriteType() {
            switch (writeTypeNumber) {
                case 0:
                    writeType = WriteType.Blog;
                    break;
                case 1:
                    writeType = WriteType.QnA;
                    break;
            }
            return writeType;
        }
    }
}
