package com.zhazha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GatewayConfig {

	@Bean
	public CorsWebFilter corsFilter() {
		UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedMethod("*");
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		configSource.registerCorsConfiguration("/**", config);
		return new CorsWebFilter(configSource);
	}

}
