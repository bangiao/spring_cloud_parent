package com.zhazha.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhazha.springcloud.GateWayMain9527;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GateWayMain9527.class)
public class DiscoveryTest {
	
	@Resource
	private RouteDefinitionWriter definitionWriter;
	@Resource
	private RouteLocator routeLocator;
	@Resource
	private RouteDefinitionLocator routeDefinitionLocator;
	@Resource
	private ObjectMapper objectMapper;
	
	@Test
	public void testDiscovery() throws Exception {
		saveRouteDefinition();
//		routeDefinitionLocator.getRouteDefinitions().subscribe(routeDefinition -> {
//			System.out.println(routeDefinition.getId());
//		});
//		routeLocator.getRoutes().subscribe(route -> {
//			System.out.println(route.getId());
//		});
	}
	
	private void saveRouteDefinition() throws JsonProcessingException {
		RouteDefinition definition = new RouteDefinition();
		definition.setId("provider_payment_consul_routh");
		definition.setOrder(0);
		definition.setUri(URI.create("lb://consul-provider-payment"));
		HashMap<String, Object> metadata = new HashMap<>();
		metadata.put("username", "zhazha");
		metadata.put("password", "123456");
		definition.setMetadata(metadata);
		ArrayList<PredicateDefinition> predicates = new ArrayList<>();
		PredicateDefinition predicateDefinition = new PredicateDefinition();
		predicateDefinition.setName("Path");
		predicateDefinition.addArg("pattern", "/payment/consul/**");
		predicates.add(predicateDefinition);
		definition.setPredicates(predicates);
//		ArrayList<FilterDefinition> filters = new ArrayList<>();
//		FilterDefinition filterDefinition = new FilterDefinition();
//		filterDefinition.setName("FallbackHeaders");
//		filterDefinition.addArg("executionExceptionTypeHeaderName", "Test-Header"
//		);
//		filters.add(filterDefinition);
//		definition.setFilters(filters);
		System.err.println(objectMapper.writeValueAsString(definition));
//		definitionWriter.save(Mono.just(definition)).subscribe();
	}
	
}
