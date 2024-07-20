package com.ivlev.javaSpringStoreSite.feign;


import com.ivlev.javaSpringStoreSite.model.*;
import com.ivlev.javaSpringStoreSite.model.dto.ProductDto;
import com.ivlev.javaSpringStoreSite.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "auth", url = "http://localhost:8081/auth")
public interface FeignAuthImpl {

    @PostMapping("/refresh-token")
    ResponseEntity<RefreshAuthResponse> refreshAuth(@RequestBody RefreshAuthRequest request);

    @PostMapping("/sign-in")
    ResponseEntity<SignInAuthResponse> signInUser(@RequestBody SignInRequest signInRequest);

    @PostMapping("/logout")
    ResponseEntity<?> logout(@RequestHeader("Authorization") String token);

    @PostMapping("/register")
    void userRegistration(@RequestBody CreateUserRequest createUserRequest);

}

