package com.example.ubergateway.controller;

import com.example.ubergateway.entity.Order;
import com.example.ubergateway.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class MyController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${uber-order-service}")
    private String orderUrl;

    @Bean
    @LoadBalanced
    public RestTemplate getRest() {
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder getBuilder() {
        return WebClient.builder();
    }

    @GetMapping("user")
    public User getUser() {
        return new User(1L, "BO");
    }

    @GetMapping("order")
    public List<Order> getOrder() {
        List<Order> orders = new ArrayList<>();
        Order o1 = restTemplate.getForObject(orderUrl + "order", Order.class);
        Order o2 = restTemplate.getForObject(orderUrl + "order", Order.class);
        orders.add(o1);
        orders.add(o2);
        return orders;
    }

    @GetMapping("order2")
    public Mono<List<Order>> getOrder2() {
        List<Order> orders = new ArrayList<>();
        Mono<Order> o1 = webClientBuilder.build()
                .get()
                .uri(orderUrl + "order")
                .retrieve()
                .bodyToMono(Order.class)
                .doOnSuccess(orders::add);
        Mono<Order> o2 = webClientBuilder.build()
                .get()
                .uri(orderUrl + "order")
                .retrieve()
                .bodyToMono(Order.class)
                .doOnSuccess(orders::add);
        return Mono.when(o1, o2).thenReturn(orders);
    }

}
