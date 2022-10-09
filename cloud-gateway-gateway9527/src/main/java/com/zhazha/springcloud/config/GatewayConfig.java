package com.zhazha.springcloud.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
//	/**
//	 * 自定义限流处理器
//	 */
//	@PostConstruct
//	public void initBlockHandlers() {
//		BlockRequestHandler blockHandler = (serverWebExchange, throwable) -> {
//			Map map = new HashMap();
//			map.put("code", 200);
//			map.put("message", "请求失败，稍后重试！");
//			return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(map));
//		};
//		GatewayCallbackManager.setBlockHandler(blockHandler);
//	}

    /**
     * 自定义 route
     */
//	@Bean
//	public RouteLocator routes(RouteLocatorBuilder builder, WhiteListGatewayFilterFactory factory) {
//		return builder
//				.routes()
//				.route("provider_payment_consul_routh", p -> p.path("/provider/**")
//						.filters(f -> f
//								.filter(factory
//										.apply(new WhiteListGatewayFilterFactory.Config("zhazha,xixi")))
//								.redirect(302, "https://www.baidu.com"))
//						.uri("lb://consul-provider-payment"))
//				.build();
//	}
	
}