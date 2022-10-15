package com.zhazha.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxDemo {
	
	@Test
	public void testFlux() throws Exception {
		Flux.defer(() -> Flux.just("just0", "just1", "just2")).subscribe(System.out::println);
	}
	
}
