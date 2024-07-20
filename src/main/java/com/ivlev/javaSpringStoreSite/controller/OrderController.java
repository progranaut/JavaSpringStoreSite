package com.ivlev.javaSpringStoreSite.controller;

import com.ivlev.javaSpringStoreSite.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all-current-user-orders")
    public ResponseEntity<?> getAllCurrentUserOrders(HttpServletRequest request) {
        return orderService.getAllCurrentUserOrders(request);
    }

    @GetMapping("/all-user-order/{id}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable String id, HttpServletRequest request) {
        return orderService.getOrderByUserId(UUID.fromString(id), request);
    }

}
