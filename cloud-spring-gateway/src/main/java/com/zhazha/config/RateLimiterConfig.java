package com.zhazha.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class RateLimiterConfig {
	
	/**
	 * 这是一个定义限流规则的接口
	 *
	 * @return
	 */
	@Bean("remoteAddrKeyResolver")
	public KeyResolver remoteAddrKeyResolver() {
		return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress());
	}
	
	@Bean("pathKeyResolver")
	public KeyResolver pathKeyResolver() {
		return exchange -> {
			var route = (Route) exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
			assert route != null;
			System.err.println("pathKeyResolver 限流 ip: " + route.getId());
			return Mono.just(exchange.getRequest().getPath().toString());
		};
	}
	
}
