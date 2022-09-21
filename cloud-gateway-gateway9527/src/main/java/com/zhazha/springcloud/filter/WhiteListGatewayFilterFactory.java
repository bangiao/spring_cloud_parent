package com.zhazha.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@Component
//@Slf4j
public class WhiteListGatewayFilterFactory extends AbstractGatewayFilterFactory<WhiteListGatewayFilterFactory.Config> {
	
	public WhiteListGatewayFilterFactory() {
		super(Config.class);
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		String value = config.getValue();
		List<String> whiteList = Arrays.stream(value.split(",")).map(s -> s.replace(" ", "")).collect(Collectors.toList());
		return new WhiteListGatewayFilter(whiteList);
	}
	
	public static class Config {
		private String value;
		
		public Config(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
	}
}
