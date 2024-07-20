package com.ivlev.javaSpringStoreSite.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MvcService {

    private final AuthService authService;

    public boolean isAdmin(HttpServletRequest request){

        return authService.isAdmin(request);

    }

}
