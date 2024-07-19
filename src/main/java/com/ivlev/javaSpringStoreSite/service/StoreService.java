package com.ivlev.javaSpringStoreSite.service;

import com.ivlev.javaSpringStoreSite.entity.Auth;
import com.ivlev.javaSpringStoreSite.feign.FeignAuthImpl;
import com.ivlev.javaSpringStoreSite.feign.FeignStoreImpl;
import com.ivlev.javaSpringStoreSite.model.UserNameAndRoleResponse;
import com.ivlev.javaSpringStoreSite.model.dto.ProductDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final AuthService authService;

    private final FeignAuthImpl feignAuth;

    private final FeignStoreImpl feignStore;

    public ResponseEntity<?> getCurrentUserNameAndRole(HttpServletRequest request) {

        System.out.println(request.getSession().getId());

        String sessionId = "";

        String[] cookies = request.getHeader("Cookie").split(";");
        for (var cook : cookies) {
            String[] someCook = cook.trim().split("=");
            if (someCook[0].equals("JSESSIONID")) {
                sessionId = someCook[1];
            }
        }

        if (!authService.containAuth(sessionId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Auth auth = authService.getAuth(sessionId);

        if (auth.getTime().plusMinutes(15).isBefore(LocalDateTime.now())) {
            authService.delAuth(sessionId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        auth = authService.refreshAuth(sessionId);

        return new ResponseEntity<>(UserNameAndRoleResponse.builder()
                .name(auth.getUserName())
                .roles(new HashSet<>(auth.getRoles()))
                .build(), HttpStatus.OK);

    }

    public List<ProductDto> getAllProduct() {

        return feignStore.getAllProduct();

    }
}
