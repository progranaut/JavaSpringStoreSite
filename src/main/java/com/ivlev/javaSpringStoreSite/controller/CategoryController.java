package com.ivlev.javaSpringStoreSite.controller;

import com.ivlev.javaSpringStoreSite.model.dto.CategoryDto;
import com.ivlev.javaSpringStoreSite.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

//    @PostMapping("/add")
//    public void addCategory(@RequestBody CategoryDto categoryDto) {
//        //categoryService.addCategory(categoryDto);
//    }

    @GetMapping("/all-product-categories")
    public List<CategoryDto> getProductCategories(HttpServletRequest request){
        return categoryService.getAllProductCategories(request);
    }

}
