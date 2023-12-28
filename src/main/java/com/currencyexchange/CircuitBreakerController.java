package com.currencyexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@CircuitBreaker(name = "sample-api", fallbackMethod = "fallbackMethod")
//	@Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
//	@Retry(name = "sample-api")
//	@Retry(name = "")
	@GetMapping("sample-api")
	public String sampleApi() {
		logger.info("======Inside sampleApi========");
		ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8200/dummy-url", String.class);
		return response.getBody();
	}
	
	@RateLimiter(name = "sample-api-rate")
	@GetMapping("sample-api/ratelimit")
	public String sampleApiForRateLimit() {
		logger.info("======Inside sampleApi rate limit========");
		return "sampleApi rate limit";
	}
	
	public String fallbackMethod(Exception e) {
		
		return "fall back method...";
	}
}
