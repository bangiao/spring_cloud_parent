package com.zhazha.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GatewayConfig {

	@Bean
	@ConditionalOnMissingBean
	public CorsWebFilter corsFilter() {
		UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedMethod("*");
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		configSource.registerCorsConfiguration("/**", config);
		return new CorsWebFilter(configSource);
	}
	
	@Primary
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Bean("yml")
	public ObjectMapper objectMapperYaml() {
		return new ObjectMapper(new YAMLFactory());
	}

}
