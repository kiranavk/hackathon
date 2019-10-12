package com.hcl.hackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

@SpringBootApplication(scanBasePackages = "com.*")
public class HackathonApplication {
	
	@Autowired
	JWTFilter jwtFilter;

	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean
	public FilterRegistrationBean<OncePerRequestFilter> jwtFilter() {
		FilterRegistrationBean<OncePerRequestFilter> regBean = new FilterRegistrationBean<>();
		regBean.setFilter(jwtFilter);
		return regBean;
	}
}
