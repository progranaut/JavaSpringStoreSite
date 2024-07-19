package com.ivlev.javaSpringStoreSite.feign;


import com.ivlev.javaSpringStoreSite.model.RefreshAuthResponse;
import com.ivlev.javaSpringStoreSite.model.SignInAuthResponse;
import com.ivlev.javaSpringStoreSite.model.SignInRequest;
import com.ivlev.javaSpringStoreSite.model.RefreshAuthRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "client", url = "http://localhost:8081")
public interface FeignImpl {

    @PostMapping("/auth/refresh-token")
    ResponseEntity<RefreshAuthResponse> refreshAuth(@RequestBody RefreshAuthRequest request);

    @PostMapping("/auth/sign-in")
    ResponseEntity<SignInAuthResponse> signInUser(@RequestBody SignInRequest signInRequest);

}

