package com.ivlev.javaSpringStoreSite.service;

import com.ivlev.javaSpringStoreSite.feign.FeignOrderImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final FeignOrderImpl feignOrder;

    private final AuthService authService;

    public ResponseEntity<?> getAllCurrentUserOrders(HttpServletRequest request) {

        String token = authService.getToken(request);
        return feignOrder.getAllCurrentUserOrders("Bearer " + token);

    }

    public ResponseEntity<?> getOrderByUserId(UUID uuid, HttpServletRequest request) {

        String token = authService.getToken(request);
        return feignOrder.getOrderByUserId("Bearer " + token, uuid);

    }
}
