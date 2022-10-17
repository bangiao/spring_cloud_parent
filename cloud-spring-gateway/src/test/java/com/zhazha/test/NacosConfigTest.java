package com.zhazha.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

//@SpringBootTest(classes = GatewayNacosApplication.class)
public class NacosConfigTest {
	
//	public static void main(String[] args) throws Exception {
//		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
//		List<RouteDefinition> routeDefinitions = objectMapper.readValue("---\n" +
//				                                                                "- id: \"provider_payment_nacos_routh\"\n" +
//				                                                                "  predicates:\n" +
//				                                                                "  - name: \"Path\"\n" +
//				                                                                "    args:\n" +
//				                                                                "      _genkey_0: \"/payment/nacos/**\"\n" +
//				                                                                "  - name: \"Method\"\n" +
//				                                                                "    args:\n" +
//				                                                                "      _genkey_0: \"GET\"\n" +
//				                                                                "  filters:\n" +
//				                                                                "  - name: \"RequestRateLimiter\"\n" +
//				                                                                "    args:\n" +
//				                                                                "      redis-rate-limiter.replenishRate: \"1\"\n" +
//				                                                                "      redis-rate-limiter.burstCapacity: \"10\"\n" +
//				                                                                "      key-resolver: \"#{@remoteAddrKeyResolver}\"\n" +
//				                                                                "  - name: \"RequestRateLimiter\"\n" +
//				                                                                "    args:\n" +
//				                                                                "      redis-rate-limiter.replenishRate: \"1\"\n" +
//				                                                                "      redis-rate-limiter.burstCapacity: \"10\"\n" +
//				                                                                "      key-resolver: \"#{@pathKeyResolver}\"\n" +
//				                                                                "  uri: \"lb://nacos-provider-payment\"\n" +
//				                                                                "  metadata: {}\n" +
//				                                                                "  order: 0", new TypeReference<>() {
//		});
//		routeDefinitions.forEach(System.err::println);
//	}
	
	public static void main(String[] args) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		RouteDefinition routeDefinition = new RouteDefinition();
		routeDefinition.setId("provider_payment_nacos_config_routh");
		ArrayList<PredicateDefinition> predicates = Lists.newArrayList();
		PredicateDefinition predicateDefinition = new PredicateDefinition();
		predicateDefinition.setName("Path");
		predicateDefinition.addArg("_genkey_0","/payment/nacos/**");
		predicates.add(predicateDefinition);
		routeDefinition.setPredicates(predicates);
		ArrayList<FilterDefinition> filters = Lists.newArrayList();
		FilterDefinition filterDefinition = new FilterDefinition();
		filterDefinition.setName("RequestRateLimiter");
		filterDefinition.addArg("redis-rate-limiter.replenishRate", "1");
		filterDefinition.addArg("redis-rate-limiter.burstCapacity", "10");
		filterDefinition.addArg("key-resolver", "#{@remoteAddrKeyResolver}");
		filters.add(filterDefinition);
		filterDefinition = new FilterDefinition();
		filterDefinition.setName("RequestRateLimiter");
		filterDefinition.addArg("redis-rate-limiter.replenishRate", "1");
		filterDefinition.addArg("redis-rate-limiter.burstCapacity", "10");
		filterDefinition.addArg("key-resolver", "#{@pathKeyResolver}");
		filters.add(filterDefinition);
		routeDefinition.setFilters(filters);
		routeDefinition.setUri(new URI("lb://nacos-provider-payment"));
		routeDefinition.setOrder(0);
		HashMap<String, Object> metadata = Maps.newHashMap();
		metadata.put("username", "admin");
		metadata.put("password", "123456");
		routeDefinition.setMetadata(metadata);
		System.err.println(objectMapper.writeValueAsString(routeDefinition));
	}
	
//	@NacosConfigListener(properties = @NacosProperties(), dataId = "")
//	public static void main(String[] args) throws Exception {
//		Properties properties = new Properties();
//		properties.setProperty(PropertyKeyConst.SERVER_ADDR, "127.0.0.1:8840");
//		properties.setProperty(PropertyKeyConst.NAMESPACE, "69c8e0ee-9bce-4e3e-8129-e550367ddda6");
//		ConfigService configService = NacosFactory.createConfigService(properties);
//		configService.publishConfig("zhazha.json", "gateway_group", "username=123456");
//	}
	
}
