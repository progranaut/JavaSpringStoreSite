package com.ivlev.javaSpringStoreSite.service;

import com.ivlev.javaSpringStoreSite.feign.FeignProductImpl;
import com.ivlev.javaSpringStoreSite.model.dto.ProductDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final FeignProductImpl feignProduct;

    private final AuthService authService;

    public ProductDto addProduct(ProductDto productDto, HttpServletRequest request) {

        String token = authService.getToken(request);
        return feignProduct.addProduct("Bearer " + token, productDto);

    }

    public ProductDto changeProduct(ProductDto productDto, HttpServletRequest request) {

        String token = authService.getToken(request);
        return feignProduct.changeProduct("Bearer " + token, productDto);

    }
}
