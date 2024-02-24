package com.example.ubergateway.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@RestController
//@RequestMapping("/api/")
public class MyController {

    @Bean
    @LoadBalanced
    public RestTemplate getRest() {
        return new RestTemplate();
    }
}
