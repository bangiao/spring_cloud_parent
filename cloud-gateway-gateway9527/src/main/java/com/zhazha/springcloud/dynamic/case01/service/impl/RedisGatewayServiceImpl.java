package com.zhazha.springcloud.dynamic.case01.service.impl;

import com.zhazha.springcloud.dynamic.case01.service.RedisGatewayService;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RedisRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Service
public class RedisGatewayServiceImpl implements RedisGatewayService, ApplicationEventPublisherAware {
	
	@Resource
	private RedisRouteDefinitionRepository redisRouteDefinitionRepository;
	
	private ApplicationEventPublisher publisher;
	
	
	@Override
	public Flux<RouteDefinition> findAll() {
		return redisRouteDefinitionRepository.getRouteDefinitions();
	}
	
	@Override
	public void save(RouteDefinition routeDefinition) {
		redisRouteDefinitionRepository.save(Mono.defer(() -> Mono.just(routeDefinition))).subscribe();
		// 给gateway发出更新事件, gateway更新 route
		publisher.publishEvent(new RefreshRoutesEvent(this));
	}
	
	@Override
	public void update(RouteDefinition routeDefinition) {
		redisRouteDefinitionRepository.delete(Mono.just(routeDefinition.getId())).subscribe();
		save(routeDefinition);
	}
	
	@Override
	public Mono<RouteDefinition> get(String routeId) {
		return Mono.from(redisRouteDefinitionRepository.getRouteDefinitions().filter(routeDefinition -> routeDefinition.getId().equals(routeId)));
	}
	
	@Override
	public void delete(String routeId) {
		redisRouteDefinitionRepository.delete(Mono.just(routeId)).subscribe();
		publisher.publishEvent(new RefreshRoutesEvent(this));
	}
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}
}
