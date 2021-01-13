package com.example.uberorderservice.controler;

import com.example.uberorderservice.entity.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class MyController {
    private int count;

    @GetMapping("order")
    public Order getOrder() throws InterruptedException {
        Thread.sleep(2000);
        count++;
        return new Order(1L, String.valueOf(count));
    }
}
