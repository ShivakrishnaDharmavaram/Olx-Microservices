package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LoginDelegateImpl implements LoginDelegate{

	@Autowired
	RestTemplate restTemplate;

	@Override
	@CircuitBreaker(name="TOKEN-VALIDATION-SERVICE", fallbackMethod = "fallbackValidToken")
	public boolean isValidToken(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authToken);
		HttpEntity entity = new HttpEntity(headers);
		//		boolean isValidToken = this.restTemplate.getForObject("http://localhost:9090/olx/user/validate/token",
		//				Boolean.class);
		//		return isValidToken;
		//localhost:9090 previously localhost:9090is there instead of auth-service.
		try {
			ResponseEntity<Boolean> result = this.restTemplate.exchange("http://API-GATEWAY/olx/user/validate/token",
					HttpMethod.GET, entity, Boolean.class);
			if (result.getStatusCode() == HttpStatus.OK)
				return result.getBody();
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public String getUsername(String authToken) {
		ResponseEntity<String> responseUsername = restTemplate.getForEntity("http://API-GATEWAY/olx/user/username?auth-token="+authToken, String.class);
		return responseUsername.getBody();
	}	
}
