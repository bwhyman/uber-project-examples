package com.example.uberuserservice.controller;

import com.example.uberuserservice.dox.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/")
public class UserController {

    @PostMapping("users")
    public User postUser(@RequestBody User user) {
        log.debug("user-order: login");
        return User.builder()
                .id("1069607508454658048")
                .account(user.getAccount())
                .role("Pok8E")
                .build();
    }
}
