package com.yyc.bunnyroom.security.auth.service;

import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import com.yyc.bunnyroom.signup.model.dto.LoginUserDTO;
import com.yyc.bunnyroom.signup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserService userService;

    // 전달받은 이메일을 매개변수로 DB에서 사용자의 정보를 찾는다
    // 전달된 사용자의 개체 타입은 AuthDetails를 구현한 구현체가 된다
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        LoginUserDTO loginUserDTO = userService.findByUserEmail(userEmail);

        if (Objects.isNull(loginUserDTO)){
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다");
        }

        return new AuthDetails(loginUserDTO);
    }
}
