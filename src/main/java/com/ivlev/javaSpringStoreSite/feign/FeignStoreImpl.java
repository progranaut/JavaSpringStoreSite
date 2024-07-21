package com.ivlev.javaSpringStoreSite.feign;

import com.ivlev.javaSpringStoreSite.model.dto.ProductDto;
import com.ivlev.javaSpringStoreSite.model.dto.UserDto;
import com.ivlev.javaSpringStoreSite.model.dto.UserProductRelationDto;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "store", url = "http://localhost:8082/store")
public interface FeignStoreImpl {

    @GetMapping("/all-products")
    List<ProductDto> getAllProduct();

    @PostMapping("/add-basket")
    void addBasket(@RequestHeader("Authorization") String token, List<UserProductRelationDto> uprd);

    @GetMapping("/all-products-in-basket")
    List<UserProductRelationDto> getAllProductInBasket(@RequestHeader("Authorization") String token);

    @DeleteMapping("/delete-product-from-basket/{id}")
    ResponseEntity<UserProductRelationDto> deleteProductFromBasket(@RequestHeader("Authorization") String token,
                                                                   @PathVariable String id);

    @GetMapping("/current-user")
    UserDto getCurrentUser(@RequestHeader("Authorization") String token);

    @PostMapping("/add-in-basket/{id}")
    ResponseEntity<?> addProductInBasket(@RequestHeader("Authorization") String s, @PathVariable UUID id);

    @GetMapping("/add-order")
    ResponseEntity<?> addOrder(@RequestHeader("Authorization") String s);

    @GetMapping("/product-in-basket/{id}")
    ResponseEntity<?> productInBasket(@RequestHeader("Authorization") String s, @PathVariable UUID id);

}
