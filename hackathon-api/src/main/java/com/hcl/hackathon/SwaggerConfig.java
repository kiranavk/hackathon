package com.hcl.hackathon;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket postApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("hackathon-api")
				.apiInfo(getApiInfo()).select().paths(getPaths()).build()
				.securityContexts(Lists.newArrayList(getSecurityContext()))
				.securitySchemes(Lists.newArrayList(getApiKey()));
	}

//	private List<? extends SecurityScheme> getSecuritySchemes() {
//		List<? extends SecurityScheme> securitySchemes = new ArrayList<>();
//		securitySchemes.add(new ApiKey("Authorization", "Authorization", "Header"));
//		return new ApiKey("Authorization", "Authorization", "Header");
//	}
	
	private SecurityContext getSecurityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("/")).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authScope = new AuthorizationScope("global", "access everything");
		AuthorizationScope[] authScopes = new AuthorizationScope[1];
		authScopes[0]=authScope;
		return Lists.newArrayList(new SecurityReference("Authorization", authScopes));
	}

	private ApiKey getApiKey() {
		return new ApiKey("Authorization", "Authorization", "header");
	}

	private Predicate<String> getPaths() {
		return Predicates.or(PathSelectors.regex("/user.*"), PathSelectors.regex("/token.*"));
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("Hackathon API")
					.description("Hackathon API endpoints list")
					.license("HCL License").version("1.0").build();
	}
	
	@Bean
	public SecurityConfiguration securityInfo() {
		return new SecurityConfiguration(null, null, "localhost:8080", "Hackathon", "Bearer ", ApiKeyVehicle.HEADER, "Authorization", "");
	}
}
