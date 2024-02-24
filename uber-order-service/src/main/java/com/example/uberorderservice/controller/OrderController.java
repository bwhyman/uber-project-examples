package com.example.uberorderservice.controller;

import com.example.uberorderservice.dox.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/")
@Slf4j
public class OrderController {

    @GetMapping("orders")
    public List<Order> getOrders() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Order.builder().id(i + "").userName("name: " + i).build())
                .toList();
    }
}
