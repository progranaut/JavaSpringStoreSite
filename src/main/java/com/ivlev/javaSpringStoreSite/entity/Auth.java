package com.ivlev.javaSpringStoreSite.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auth {

    private UUID id;

    private String token;

    private String refreshToken;

    private String userName;

    private List<String> roles;

    private LocalDateTime time;


}
