package com.olx;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class OlxMasterdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxMasterdataApplication.class, args);
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	@Bean
	public Docket getCustomizeDocket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.olx"))
				.paths(PathSelectors.any())
				.build();
	}
	private ApiInfo getApiInfo() {
		return new ApiInfo("OLX MasterData REST API Documentation",
				"OLX MasterData REST API documentation released by DSK Zensar Pvt Ltd.",
				"2.2",
				"http://olx.com",
				new Contact("Shivakrishna","shivakrishnadss1999@gmail.com","https://www.linkedin.com/in/shivakrishna-dharmavaram-5002b215a/"),
				"GPL",
				"http://gpl.com",
				new ArrayList<VendorExtension>());
	}
}
