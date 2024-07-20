package com.ivlev.javaSpringStoreSite.feign;

import com.ivlev.javaSpringStoreSite.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user", url = "http://localhost:8082/users")
public interface FeignUserImpl {

    @PostMapping("/change")
    void changeUser(@RequestHeader("Authorization") String s, UserDto userDto);

}
