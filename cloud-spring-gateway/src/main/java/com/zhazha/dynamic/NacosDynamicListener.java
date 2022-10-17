package com.zhazha.dynamic;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

@Component
public class NacosDynamicListener implements ApplicationEventPublisherAware {
	
	@Value("${spring.cloud.nacos.server-addr}")
	private String serverAddr;
	private String namespace = "69c8e0ee-9bce-4e3e-8129-e550367ddda6";
	private String group = "gateway_group";
	private String dataId = "gateway-json-routes.json";
	@Resource(name = "yml")
	private ObjectMapper objectMapper;
	@Resource
	private RouteDefinitionWriter definitionWriter;
	private ConfigService configService;
	private ApplicationEventPublisher publisher;
	// 记录上次保存的动态路由
	private List<RouteDefinition> routesList = new ArrayList<>();
	
/*
---
- id: "provider_payment_nacos_config_routh"
  predicates:
  - name: "Path"
    args:
      _genkey_0: "/get/**"
  - name: "Method"
    args:
      _genkey_0: "GET"
  filters:
  - name: "RequestRateLimiter"
    args:
      redis-rate-limiter.replenishRate: "1"
      redis-rate-limiter.burstCapacity: "10"
      key-resolver: "#{@remoteAddrKeyResolver}"
  - name: "RequestRateLimiter"
    args:
      redis-rate-limiter.replenishRate: "1"
      redis-rate-limiter.burstCapacity: "10"
      key-resolver: "#{@pathKeyResolver}"
  uri: "lb://nacos-provider-payment"
  metadata: {}
  order: 0
*/
	@SneakyThrows
	@PostConstruct
	public void init() {
		// 创建 nacos config 对象
		createNacosConfigService();
		
		// 读取nacos上面的配置
		loadNacosCenterConfig();
		
		// 监听器
		nacosListener();
	}
	
	private void nacosListener() throws NacosException {
		configService.addListener(dataId, group, new Listener() {
			@Override
			public Executor getExecutor() {
				return null;
			}

			@Override
			public void receiveConfigInfo(String configInfo) {
				try {
					List<RouteDefinition> routeDefinitions = objectMapper.readValue(configInfo, new TypeReference<>() {
					});
					updateNacosDynamicRoute(routeDefinitions);
					// 告知gateway路由已更新
					publishEvent();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void publishEvent() {
		publisher.publishEvent(new RefreshRoutesEvent(this));
	}
	
	private void updateNacosDynamicRoute(List<RouteDefinition> routeDefinitions) {
		// 删除现有route
		routesList.forEach(routeDefinition -> definitionWriter.delete(Mono.just(routeDefinition.getId())).subscribe());
		routesList.clear();
		// 保存现有 route
		routeDefinitions.forEach(routeDefinition -> {
			definitionWriter.save(Mono.just(routeDefinition)).subscribe();
			routesList.add(routeDefinition);
		});
	}
	
	private void loadNacosCenterConfig() throws NacosException, JsonProcessingException {
		String config = configService.getConfig(dataId, group, 5000);
		List<RouteDefinition> routeDefinitions = objectMapper.readValue(config, new TypeReference<>() {
		});
		if (!routeDefinitions.isEmpty()) {
			routesList.addAll(routeDefinitions);
		}
		routeDefinitions.forEach(routeDefinition -> definitionWriter.save(Mono.just(routeDefinition)).subscribe());
	}
	
	private void createNacosConfigService() throws NacosException {
		Properties properties = new Properties();
		properties.setProperty(PropertyKeyConst.NAMESPACE, namespace);
		properties.setProperty(PropertyKeyConst.SERVER_ADDR, serverAddr);
		configService = NacosFactory.createConfigService(properties);
	}
	
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}
}
