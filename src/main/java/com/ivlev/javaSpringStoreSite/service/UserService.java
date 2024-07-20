package com.ivlev.javaSpringStoreSite.service;

import com.ivlev.javaSpringStoreSite.feign.FeignUserImpl;
import com.ivlev.javaSpringStoreSite.model.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final FeignUserImpl feignUser;

    private final AuthService authService;

    public void changeUser(UserDto userDto, HttpServletRequest request) {

        String token = authService.getToken(request);
        feignUser.changeUser("Bearer " + token, userDto);

    }

    public List<UserDto> getAllUsers(HttpServletRequest request) {

        String token = authService.getToken(request);
        return feignUser.getAllUsers("Bearer " + token);

    }
}
