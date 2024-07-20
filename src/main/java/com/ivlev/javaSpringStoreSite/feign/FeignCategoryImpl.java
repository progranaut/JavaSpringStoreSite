package com.ivlev.javaSpringStoreSite.feign;

import com.ivlev.javaSpringStoreSite.model.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "category", url = "http://localhost:8082/category")
public interface FeignCategoryImpl {

    @GetMapping("/all-product-categories")
    List<CategoryDto> getAllProductCategory(@RequestHeader("Authorization") String s);

}
