package com.zhazha.springcloud.dynamic.case01.service;

import org.springframework.cloud.gateway.route.RouteDefinition;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RedisGatewayService {
	
	Flux<RouteDefinition> findAll();
	
	void save(RouteDefinition routeDefinition);
	
	void update(RouteDefinition routeDefinition);
	
	Mono<RouteDefinition> get(String routeId);
	
	void delete(String routeId);
}
