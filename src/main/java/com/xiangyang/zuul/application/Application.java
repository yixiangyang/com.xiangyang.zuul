package com.xiangyang.zuul.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.xiangyang.zuul.controller", "com.xiangyang.zuul.service" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
//		com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitRepository.
	}

}

