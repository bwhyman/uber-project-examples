package com.example.ubergateway.filter;

import com.example.ubergateway.component.JWTComponent;
import com.example.ubergateway.exception.Code;
import com.example.ubergateway.vo.RequestConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(-1)
public class LoginGlobalFilter implements GlobalFilter {
    private final ResponseHelper responseHelper;
    private final JWTComponent jwtComponent;

    private final PathPattern path = new PathPatternParser().parse("/api/**");
    private final List<PathPattern> excludesS = List.of(new PathPatternParser().parse("/api/login"));
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 排除
        for (PathPattern p : excludesS) {
            if (p.matches(request.getPath().pathWithinApplication())) {
                return chain.filter(exchange);
            }
        }
        // 非过滤路径，按异常处理
        if (!path.matches(request.getPath().pathWithinApplication())) {
            return responseHelper.response(Code.BAD_REQUEST, exchange);
        }
        HttpHeaders headers = request.getHeaders();
        var token = headers.getFirst(RequestConstant.TOKEN);
        if (token == null) {
            return responseHelper.response(Code.UNAUTHORIZED, exchange);
        }
        return jwtComponent.decode(token)
                .flatMap(decode -> {
                    String uid = decode.getClaim(RequestConstant.UID).asString();
                    String role = decode.getClaim(RequestConstant.ROLE).asString();
                    // 默认header禁止修改信息
                    HttpHeaders httpHeaders = HttpHeaders.writableHttpHeaders(headers);
                    httpHeaders.set(RequestConstant.UID, uid);
                    httpHeaders.set(RequestConstant.ROLE, role);
                    return chain.filter(exchange);
                });
    }
}
