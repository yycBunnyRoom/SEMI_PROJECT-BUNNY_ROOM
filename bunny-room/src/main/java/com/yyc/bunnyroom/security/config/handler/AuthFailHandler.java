package com.yyc.bunnyroom.security.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

@Configuration
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = null;
        if (exception instanceof BadCredentialsException){
            // BadCredentialException -> 사용자의 아이디가 DB에 존재하지 않거나, 비밀번호가 맞지 않는 경우 발생
            errorMessage = "가입된 이메일이 존재하지 않거나 비밀번호가 일치하지 않습니다.";
        }
        else if (exception instanceof DisabledException){
            errorMessage = "탈퇴된 회원입니다.";
        }
        else if (exception instanceof InternalAuthenticationServiceException){
            // 서버에서 사용자 정보를 검증하는 과정에서 발생하는 에러
            errorMessage = "탈퇴된 회원입니다.";
        }
        else if (exception instanceof UsernameNotFoundException){
            // db에 사용자의 정보가 없는 경우 발생하는 오류이다
            errorMessage = "존재하지 않는 이메일 입니다.";
        }
        else if (exception instanceof AuthenticationCredentialsNotFoundException){
            // 보안 컨텍스트에 인증 객체가 존재하지 않거나 인증 정보가 없는 상태에서 보안처리된 리소스에 접근하는 경우 발생
            errorMessage = "인증 요청이 거부되었습니다.";
        }
        else {
            errorMessage = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다.";
        }



        errorMessage = URLEncoder.encode(errorMessage,"UTF-8");

        setDefaultFailureUrl("/security/auth/login?exception=" + errorMessage);

        super.onAuthenticationFailure(request, response, exception);
    }
}
