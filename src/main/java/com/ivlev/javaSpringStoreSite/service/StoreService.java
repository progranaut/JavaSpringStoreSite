package com.ivlev.javaSpringStoreSite.service;

import com.ivlev.javaSpringStoreSite.entity.Auth;
import com.ivlev.javaSpringStoreSite.feign.FeignAuthImpl;
import com.ivlev.javaSpringStoreSite.feign.FeignStoreImpl;
import com.ivlev.javaSpringStoreSite.model.UserNameAndRoleResponse;
import com.ivlev.javaSpringStoreSite.model.dto.ProductDto;
import com.ivlev.javaSpringStoreSite.model.dto.UserDto;
import com.ivlev.javaSpringStoreSite.model.dto.UserProductRelationDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final AuthService authService;

    private final FeignStoreImpl feignStore;

    public ResponseEntity<?> getCurrentUserNameAndRole(HttpServletRequest request) {

        String sessionId = request.getSession().getId();
        System.out.println(sessionId);

        if (!authService.containAuth(sessionId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Auth auth = authService.getAuth(request);

        if (auth.getTime().plusMinutes(30).isBefore(LocalDateTime.now())) {
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

    public void addBasket(List<UserProductRelationDto> uprd, HttpServletRequest request) {

        String sessionId = request.getSession().getId();
        String token = authService.getAuth(request).getToken();
        feignStore.addBasket("Bearer " + token, uprd);

    }

    public List<UserProductRelationDto> getAllProductsInBasket(HttpServletRequest request) {

        String token = authService.getToken(request);
        return feignStore.getAllProductInBasket("Bearer " + token);

    }

    public ResponseEntity<UserProductRelationDto> deleteProductFromBasket(String id, HttpServletRequest request) {

        String token = authService.getToken(request);
        ResponseEntity<UserProductRelationDto> response = feignStore.deleteProductFromBasket("Bearer " + token, id);
        if (response.getStatusCode().value() == 404) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    public UserDto getCurrentUser(HttpServletRequest request) {

        String token = authService.getToken(request);

        return feignStore.getCurrentUser("Bearer " + token);

    }

    public ResponseEntity addProductInBasket(UUID id, HttpServletRequest request) {

        String token = authService.getToken(request);
        return feignStore.addProductInBasket("Bearer " + token, id);

    }

    public ResponseEntity<?> addOrder(HttpServletRequest request) {

        String token = authService.getToken(request);
        return feignStore.addOrder("Bearer " + token);

    }

    public void userRegistration(UserDto userDto) {



    }
}
