package com.zhazha.webflux.controller;

import com.zhazha.webflux.domain.City;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
public class CityController {

    @GetMapping("hello")
    public String hello() {
    	return "Hello, WebFlux!!!";
    }

    @GetMapping("city")
    public Mono<City> getCity() {
        City city = new City();
        city.setId(12345678L);
        city.setName("beijing");
        return Mono.just(city);
    }
    
    @GetMapping(value = "flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        return Flux.fromStream(IntStream.range(1, 5).mapToObj(value -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "flux data--" + value;
        }));
    }

}