package com.ivlev.javaSpringStoreSite.controller;

import com.ivlev.javaSpringStoreSite.service.StoreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/current-user-name-roll")
    public ResponseEntity<?> userName(HttpServletRequest request) {

        return storeService.getCurrentUserNameAndRole(request);

    }

}
