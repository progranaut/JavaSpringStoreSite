package com.ivlev.javaSpringStoreSite.feign;

import com.ivlev.javaSpringStoreSite.model.dto.ProductDto;
import com.ivlev.javaSpringStoreSite.model.dto.UserProductRelationDto;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "store", url = "http://localhost:8082")
public interface FeignStoreImpl {

    @GetMapping("/store/all-products")
    List<ProductDto> getAllProduct();

    @PostMapping("/store/add-basket")
    void addBasket(@RequestHeader("Authorization") String token, List<UserProductRelationDto> uprd);

    @GetMapping("/store/all-products-in-basket")
    List<UserProductRelationDto> getAllProductInBasket(@RequestHeader("Authorization") String token);


    @DeleteMapping("/store/delete-product-from-basket/{id}")
    ResponseEntity<UserProductRelationDto> deleteProductFromBasket(@RequestHeader("Authorization") String token, @PathVariable String id);

}
