package com.example.ubergateway.controller;

import com.example.ubergateway.component.JWTComponent;
import com.example.ubergateway.dox.User;
import com.example.ubergateway.exception.Code;
import com.example.ubergateway.httpexchange.UserService;
import com.example.ubergateway.vo.RequestConstant;
import com.example.ubergateway.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/")
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final JWTComponent jwtComponent;

    @PostMapping("login")
    public Mono<ResultVO> postLogin(@RequestBody User user, ServerHttpResponse response) {
        log.debug("gateway: login");
        return userService.getUser(user)
                .map(u -> {
                    Map<String, Object> tokenM = Map.of(
                            RequestConstant.UID, u.getId(),
                            RequestConstant.ROLE, u.getRole());
                    String token = jwtComponent.encode(tokenM);
                    HttpHeaders headers = response.getHeaders();
                    headers.add("token", token);
                    headers.add("role", u.getRole());
                    return ResultVO.success(Map.of("user", u));
                })
                .defaultIfEmpty(ResultVO.error(Code.LOGIN_ERROR));
    }
}
