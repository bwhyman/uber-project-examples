package com.example.ubergateway.httpexchange;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private HttpServiceProxyFactory factory;
    private final Environment environment;
    private final ReactorLoadBalancerExchangeFilterFunction filterFunction;

    @PostConstruct
    public void init() {
        WebClient client = WebClient.builder()
                // 支持负载均衡的注册中心地址
                .filter(filterFunction)
                .build();
        factory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(client))
                // 支持在接口声明base地址
                .embeddedValueResolver(environment::resolvePlaceholders)
                .build();
    }

    @Bean
    public UserService userOrderExchange() {
        return factory.createClient(UserService.class);
    }
}
