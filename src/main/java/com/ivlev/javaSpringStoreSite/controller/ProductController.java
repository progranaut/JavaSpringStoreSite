package com.ivlev.javaSpringStoreSite.controller;

import com.ivlev.javaSpringStoreSite.model.dto.ProductDto;
import com.ivlev.javaSpringStoreSite.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ProductDto addProduct(@RequestBody ProductDto productDto, HttpServletRequest request) {

        return productService.addProduct(productDto, request);

    }

    @PostMapping("/change")
    public ProductDto changeProduct(@RequestBody ProductDto productDto, HttpServletRequest request) {
        return productService.changeProduct(productDto, request);
    }

}
