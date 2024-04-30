package com.example.ubergateway.httpexchange;

import com.example.ubergateway.dox.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

@HttpExchange("${api.user-service-url}")
public interface UserService {

    @PostExchange("users")
    Mono<User> getUser(@RequestBody User user);

}
