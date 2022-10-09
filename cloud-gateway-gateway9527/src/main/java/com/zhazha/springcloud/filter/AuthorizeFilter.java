package com.zhazha.springcloud.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 案例：全局过滤器判断请求中是否存在token
 */
//@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String token = exchange.getRequest().getQueryParams().getFirst("token");
		if (StringUtils.isBlank(token)) {
			exchange.getResponse()
					.setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}
	
	@Override
	public int getOrder() {
		return 0;
	}
}
