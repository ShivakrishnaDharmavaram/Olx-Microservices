package com.olx.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/olx/masterData")
public class SampleController {

	@Value("${spring.datasource.url}")
	String dbUrl;
	
	@GetMapping(value="/db/url")
	public String getDbUrl() {
		return "DB Url : " + this.dbUrl;
	}
}
