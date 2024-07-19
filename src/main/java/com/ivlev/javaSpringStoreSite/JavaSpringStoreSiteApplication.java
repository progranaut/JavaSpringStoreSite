package com.ivlev.javaSpringStoreSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JavaSpringStoreSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringStoreSiteApplication.class, args);
	}

}
