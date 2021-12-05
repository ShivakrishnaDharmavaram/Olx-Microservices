package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.repo.BlacklistTokenRepo;
// in browser http://localhost:9090/read-config
//http://localhost:9090/olx-login
@RestController
@RefreshScope
@RequestMapping("olx/user")
public class SampleController {
	
	@Autowired
	BlacklistTokenRepo tokenRepository;
	
	@Value("${spring.datasource.url}")
	String dbUrl;
	
	@GetMapping(value="/read-config")
	public String getDbUrl() {
		return "DB URL: "+this.dbUrl;
		
	}
}


// in browser http://localhost:9090/read-config