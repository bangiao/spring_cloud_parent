package com.zhazha.webflux.controller;

import com.zhazha.webflux.domain.City;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CityController {

    @GetMapping("hello")
    public String hello() {
    	return "Hello, WebFlux!!!";
    }

    @GetMapping("user")
    public Mono<City> getCity() {
        City city = new City();
        city.setId(12345678L);
        city.setName("beijing");
        return Mono.just(city);
    }

}