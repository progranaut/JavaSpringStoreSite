package com.ivlev.javaSpringStoreSite.controller;

import com.ivlev.javaSpringStoreSite.model.SignInRequest;
import com.ivlev.javaSpringStoreSite.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signInUser(@RequestBody SignInRequest signInRequest, HttpServletRequest request, HttpServletResponse response) {
        return authService.signInUser(signInRequest, request, response);
    }

}
