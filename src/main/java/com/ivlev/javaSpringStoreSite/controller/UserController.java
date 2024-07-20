package com.ivlev.javaSpringStoreSite.controller;

import com.ivlev.javaSpringStoreSite.model.dto.UserDto;
import com.ivlev.javaSpringStoreSite.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/change")
    public void changeUser(@RequestBody UserDto userDto, HttpServletRequest request) {
        userService.changeUser(userDto, request);
    }

}
