package com.example.uberorderservice.controller;

import com.example.uberorderservice.dox.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/")
@Slf4j
public class OrderController {

    @GetMapping("orders")
    public List<Order> getOrders(@RequestHeader("uid") String uid) {
        log.debug(uid);
        return IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Order.builder().id(i + "").userName("name: " + i).build())
                .toList();
    }
}
