package com.ivlev.javaSpringStoreSite.service;

import com.ivlev.javaSpringStoreSite.feign.FeignMessageImpl;
import com.ivlev.javaSpringStoreSite.model.dto.CallOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final FeignMessageImpl feignMessage;

    public void callOrder(CallOrderDto callOrderDto) {

        feignMessage.callOrder(callOrderDto);

    }
}
