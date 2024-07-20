package com.ivlev.javaSpringStoreSite.service;

import com.ivlev.javaSpringStoreSite.feign.FeignUserImpl;
import com.ivlev.javaSpringStoreSite.model.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final FeignUserImpl feignUser;

    private final AuthService authService;

    public void changeUser(UserDto userDto, HttpServletRequest request) {

        String token = authService.getToken(request);
        //userDto.setId(authService.getAuth(request).getId());
        feignUser.changeUser("Bearer " + token, userDto);

    }

}
