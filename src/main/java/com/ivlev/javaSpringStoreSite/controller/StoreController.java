package com.ivlev.javaSpringStoreSite.controller;

import com.ivlev.javaSpringStoreSite.model.dto.ProductDto;
import com.ivlev.javaSpringStoreSite.model.dto.UserDto;
import com.ivlev.javaSpringStoreSite.model.dto.UserProductRelationDto;
import com.ivlev.javaSpringStoreSite.service.StoreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/current-user-name-roll")
    public ResponseEntity<?> userName(HttpServletRequest request) {
        return storeService.getCurrentUserNameAndRole(request);
    }

    @GetMapping("/all-products")
    public List<ProductDto> allProducts() {
        return storeService.getAllProduct();
    }

    @PostMapping("/add-basket")
    public void addBasket(@RequestBody List<UserProductRelationDto> uprd, HttpServletRequest request){
        storeService.addBasket(uprd, request);
    }

    @GetMapping("/all-products-in-basket")
    public List<UserProductRelationDto> getProductsInCart (HttpServletRequest request) {
        return storeService.getAllProductsInBasket(request);
    }

    @GetMapping("/delete-product-from-basket/{id}")
    public ResponseEntity<UserProductRelationDto> deleteProductInBasket(@PathVariable String id, HttpServletRequest request) {
        return storeService.deleteProductFromBasket(id, request);
    }

    @GetMapping("/current-user")
    public UserDto getCurrentUser(HttpServletRequest request) {
        return storeService.getCurrentUser(request);
    }

}
