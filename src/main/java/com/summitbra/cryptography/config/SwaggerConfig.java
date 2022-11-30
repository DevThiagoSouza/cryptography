package com.summitbra.cryptography.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig  {

    // Para acessar o swagger basta acessar o link e depois autenticar com token: http://localhost:8080/swagger-ui/index.html

    private static final String BEARER_AUTH = "Bearer";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes()).securityContexts(List.of(securityContext()));
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Register employee")
                .description("Aplicacção back-end para CRUD usuarios")
                .version("1.0")
                .contact(contact())
                .build();
    }

    private Contact contact(){
        return new Contact("Summit-BRA ",
                "http://summit-bra.com/",
                "adm@summit-bra.com.br");
    }

    private List<SecurityScheme> securitySchemes() {
        return List.of(new ApiKey(BEARER_AUTH, "Authorization", "header"));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(List.of(bearerAuthReference())).forPaths(PathSelectors.ant("/**")).build();
    }

    private SecurityReference bearerAuthReference() {
        return new SecurityReference(BEARER_AUTH, new AuthorizationScope[0]);
    }
}
