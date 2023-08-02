package com.example.blog.dto.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "스프링 시큐리티 기반 인증 시, 계층간 사용 유저 정보 DTO")
public class UserDto {

    @ApiModelProperty(position = 1 ,  value = "유저 pk", required = false)
    private Long userId;
    @ApiModelProperty(position = 2 ,  value = "유저명", required = false)
    private String userName;
    @ApiModelProperty(position = 3 ,  value = "유저 이메일 (ID)",required = true)
    private String userEmail;
    @ApiModelProperty(position = 4 ,  value = "유저 비밀번호 (암호화)",required = true)
    private String userPassword;

}
