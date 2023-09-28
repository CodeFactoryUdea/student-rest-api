package com.code.factory.stundetrestapi.facade;


import com.code.factory.stundetrestapi.config.jwt.GenerateJWT;
import com.code.factory.stundetrestapi.dto.UserDto;
import com.code.factory.stundetrestapi.dto.UserSingIn;
import com.code.factory.stundetrestapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class UserFacade {

    private GenerateJWT generateJWT;

    private AuthenticationManager authenticationManager;

    private UserService userService;

    public UserSingIn authentication(UserDto userDto) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = generateJWT.generateToken(authentication);
        UserSingIn userSingIn = new UserSingIn();
        userSingIn.setTokenType("Bearer");
        userSingIn.setToken(jwt);

        return userSingIn;

    }





}
