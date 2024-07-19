package com.ivlev.javaSpringStoreSite.feign;

import com.ivlev.javaSpringStoreSite.model.dto.ProductDto;
import com.ivlev.javaSpringStoreSite.model.dto.UserProductRelationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "store", url = "http://localhost:8082")
public interface FeignStoreImpl {

    @GetMapping("/store/all-products")
    List<ProductDto> getAllProduct();

    @PostMapping("/store/add-basket")
    void addBasket(@RequestHeader("Authorization") String token, List<UserProductRelationDto> uprd);
}
