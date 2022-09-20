package com.zhazha.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

public class WhiteListGatewayFilter implements GatewayFilter, Ordered {
	
	private List<String> whiteList;
	
	public WhiteListGatewayFilter(List<String> whiteList) {
		this.whiteList = whiteList;
	}
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String username = exchange.getRequest().getQueryParams().getFirst("username");
		if (whiteList.isEmpty() || !whiteList.contains(username)) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}
	
	/**
	 * 优先级
	 *
	 * @return
	 */
	@Override
	public int getOrder() {
		return 0;
	}
}
