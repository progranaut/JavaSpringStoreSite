package com.ivlev.javaSpringStoreSite.controller;

import com.ivlev.javaSpringStoreSite.model.CreateUserRequest;
import com.ivlev.javaSpringStoreSite.model.SignInRequest;
import com.ivlev.javaSpringStoreSite.model.dto.UserDto;
import com.ivlev.javaSpringStoreSite.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signInUser(@RequestBody SignInRequest signInRequest, HttpServletRequest request, HttpServletResponse response) {
        return authService.signInUser(signInRequest, request, response);
    }

    @GetMapping("/logout")
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.logout(request);
        response.sendRedirect("http://localhost:8080/home");
    }

    @PostMapping("/user-registration")
    public void userRegistrationV2(@RequestBody CreateUserRequest createUserRequest) {
        authService.userRegistration(createUserRequest);
    }

}
