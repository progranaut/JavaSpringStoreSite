package com.ivlev.javaSpringStoreSite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNameAndRoleResponse {

    private String name;

    private Set<String> roles;

}
