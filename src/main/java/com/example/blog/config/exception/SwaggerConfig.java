package com.example.blog.config.exception;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private static final String BASE_PACKAGE_PATH = "com.example.blog";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE_PATH))
                .paths(PathSelectors.ant("/**"))
                .paths(PathSelectors.any())
                .build()
                .groupName("API 1.0.0") // group별 명칭을 주어야 한다.
                .pathMapping("/")
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false); // 400,404,500 .. 표기를 ui에서 삭제한다.
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Side_BlogType API Docs")
                .description("블로그 타입 사이드 프로젝트 API 명세")
                .version("1.0.0")
                .license("")
                .licenseUrl("")
                .build();
    }

    // FIXME : JWT 인증 로직 추가 후 스웨거 인증 같이 추가 예정

}
