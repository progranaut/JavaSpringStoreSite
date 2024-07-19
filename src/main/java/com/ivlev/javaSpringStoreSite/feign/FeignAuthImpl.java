package com.ivlev.javaSpringStoreSite.feign;


import com.ivlev.javaSpringStoreSite.model.RefreshAuthResponse;
import com.ivlev.javaSpringStoreSite.model.SignInAuthResponse;
import com.ivlev.javaSpringStoreSite.model.SignInRequest;
import com.ivlev.javaSpringStoreSite.model.RefreshAuthRequest;
import com.ivlev.javaSpringStoreSite.model.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "auth", url = "http://localhost:8081")
public interface FeignAuthImpl {

    @PostMapping("/auth/refresh-token")
    ResponseEntity<RefreshAuthResponse> refreshAuth(@RequestBody RefreshAuthRequest request);

    @PostMapping("/auth/sign-in")
    ResponseEntity<SignInAuthResponse> signInUser(@RequestBody SignInRequest signInRequest);

    @PostMapping("/auth/logout")
    ResponseEntity<?> logout(@RequestHeader("Authorization") String token);

}

