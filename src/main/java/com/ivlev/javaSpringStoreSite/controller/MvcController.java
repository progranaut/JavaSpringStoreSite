package com.ivlev.javaSpringStoreSite.controller;

import com.ivlev.javaSpringStoreSite.service.MvcService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MvcController {

    private final MvcService mvcService;

    @GetMapping("/")
    public String ho(){
        return "home";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/admin")
    public String admin(HttpServletRequest request) {
        if (mvcService.isAdmin(request)) {
            return "admin";
        }
        return "home";
    }

}
