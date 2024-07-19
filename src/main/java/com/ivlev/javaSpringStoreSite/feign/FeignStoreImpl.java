package com.ivlev.javaSpringStoreSite.feign;

import com.ivlev.javaSpringStoreSite.model.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "store", url = "http://localhost:8082")
public interface FeignStoreImpl {

    @GetMapping("/store/all-products")
    List<ProductDto> getAllProduct();

}
