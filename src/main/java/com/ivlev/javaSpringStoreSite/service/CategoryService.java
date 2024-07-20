package com.ivlev.javaSpringStoreSite.service;

import com.ivlev.javaSpringStoreSite.feign.FeignCategoryImpl;
import com.ivlev.javaSpringStoreSite.model.dto.CategoryDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final FeignCategoryImpl feignCategory;

    private final AuthService authService;

    public List<CategoryDto> getAllProductCategories(HttpServletRequest request) {

        String token = authService.getToken(request);
        return feignCategory.getAllProductCategory("Bearer " + token);

    }
}
