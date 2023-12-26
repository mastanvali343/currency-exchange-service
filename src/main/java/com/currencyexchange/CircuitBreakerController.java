package com.currencyexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
//	@Retry(name = "sample-api")
//	@Retry(name = "")
	@GetMapping("sample-api")
	public String sampleApi() {
		logger.info("======Inside sampleApi========");
		ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8200/dummy-url", String.class);
		return response.getBody();
	}
	
	public String fallbackMethod(Exception e) {
		System.getenv().entrySet().stream().forEach(entry-> System.out.println(entry.getKey()+" ====> "+entry.getValue()));
		System.out.println("================================================");
		System.getProperties().entrySet().forEach(entry-> System.out.println(entry.getKey()+" ====> "+entry.getValue()));
		return "fall back method...";
	}
}
