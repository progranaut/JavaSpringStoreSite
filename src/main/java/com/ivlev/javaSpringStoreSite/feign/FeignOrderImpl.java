package com.ivlev.javaSpringStoreSite.feign;

import com.ivlev.javaSpringStoreSite.model.dto.ProductDto;
import com.ivlev.javaSpringStoreSite.model.dto.UserDto;
import com.ivlev.javaSpringStoreSite.model.dto.UserProductRelationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "order", url = "http://localhost:8082/orders")
public interface FeignOrderImpl {

    @GetMapping("/all-current-user-orders")
    ResponseEntity<?> getAllCurrentUserOrders(@RequestHeader("Authorization") String s);

}
