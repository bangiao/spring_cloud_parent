package com.zhazha.springcloud.dynamic.case01.controller;

import com.zhazha.springcloud.dynamic.case01.service.RedisGatewayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@Slf4j
public class RedisGatewayController {
	
	@Resource
	private RedisGatewayService redisGatewayService;
	
	@GetMapping("findAll")
	public Flux<RouteDefinition> findAll() {
		return redisGatewayService.findAll();
	}
	
	@PostMapping("save")
	public Mono<Void> save(@RequestBody RouteDefinition routeDefinition) {
		redisGatewayService.save(routeDefinition);
		return Mono.empty();
	}
	
	@PutMapping("update")
	public Mono<Void> update(@RequestBody RouteDefinition routeDefinition) {
		redisGatewayService.update(routeDefinition);
		return Mono.empty();
	}
	
	@GetMapping("get")
	public Mono<RouteDefinition> get(String routeId) {
		return redisGatewayService.get(routeId);
	}
	
	@DeleteMapping("delete")
	public Mono<ResponseEntity<Object>> delete(String routeId) {
		return redisGatewayService.delete(routeId);
	}
	
}