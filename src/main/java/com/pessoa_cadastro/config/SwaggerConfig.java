package com.pessoa_cadastro.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket hospedeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()								   
                .apis(RequestHandlerSelectors.basePackage("com.pessoa_cadastro.controller"))               
                .build()
                .apiInfo(metaInfo());
    }

	private ApiInfo metaInfo() {

        return new ApiInfo(
                "Prova de desenvolvedor da Eloware  ",
                "API Prova de desenvolvedor da Eloware .",
                "1.0",
                "Terms of Service",
                new Contact("eusoubrunohorn@gmail.com","Bruno Horn Jr","https://www.linkedin.com/in/bruno-horn-552b84181/"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<>()
        );
    }	

}

