package com.ivlev.javaSpringStoreSite.feign;

import com.ivlev.javaSpringStoreSite.model.dto.CallOrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "message", url = "http://localhost:8082/message")
public interface FeignMessageImpl {

    @PostMapping("/call-order")
    void callOrder(@RequestBody CallOrderDto callOrderDto);
}
