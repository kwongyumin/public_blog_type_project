package com.example.blog.config.interceptor;

import com.example.blog.common.codes.ErrorCode;
import com.example.blog.common.util.TokenUtils;
import com.example.blog.config.exception.BusinessExceptionHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * JwtTokenInterceptor는 클라이언트로부터 전송된 요청의 헤더에서 JWT 토큰을 추출하고, 이를 검증하여 유효한 토큰인지 확인하는 역할을 한다.
 * @fileName JwtTokenInterceptor
 */
@Log4j2
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String header = request.getHeader("Authorization");

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            log.debug("if request options method is options, return true");

            return true;
        }

        if (header != null) {

            String token = TokenUtils.getTokenFromHeader(header);

            if (TokenUtils.isValidToken(token)) {
                String userId = TokenUtils.getUserIdFromToken(token);
                if (userId == null) {
                    log.debug("token isn't userId");
                    throw new BusinessExceptionHandler("token isn't userId", ErrorCode.AUTH_TOKEN_NOT_MATCH);
                }
                return true;
            } else {
                throw new BusinessExceptionHandler("token is invalid", ErrorCode.AUTH_TOKEN_INVALID);
            }
        } else {
            throw new BusinessExceptionHandler("Header not exist token", ErrorCode.AUTH_TOKEN_IS_NULL);
        }
    }
}