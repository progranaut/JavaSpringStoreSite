package com.ivlev.javaSpringStoreSite.feign;

import com.ivlev.javaSpringStoreSite.model.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "product", url = "http://localhost:8082/products")
public interface FeignProductImpl {

    @PostMapping("/add")
    ProductDto addProduct(@RequestHeader("Authorization") String s, @RequestBody ProductDto productDto);

    @PostMapping("/change")
    ProductDto changeProduct(@RequestHeader("Authorization") String s, @RequestBody ProductDto productDto);
}
