package com.zhazha.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

//@Component
public class IPAddressStatisticsFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        InetSocketAddress host = exchange.getRequest().getHeaders().getHost();
        if (host == null || host.getHostName() == null) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return exchange.getResponse().setComplete();
        }
        String hostName = host.getHostName();
        AtomicInteger count = IpCache.CACHE.getOrDefault(hostName, new AtomicInteger(0));
        count.incrementAndGet();
        IpCache.CACHE.put(hostName, count);
        System.out.println("IP地址：" + hostName + ",访问次数：" + count.intValue());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 123;
    }
}
