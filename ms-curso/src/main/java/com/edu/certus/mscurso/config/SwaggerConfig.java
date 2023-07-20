package com.edu.certus.mscurso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
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
    Docket api() {
        return new Docket( DocumentationType.SWAGGER_2 )
            .apiInfo( getApiInfo() )
            .select()
            .apis( RequestHandlerSelectors.basePackage( "com.edu.certus.mscurso" ) )
            .paths( PathSelectors.any() )
            .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
            .title( "Microservicio Curso" )
            .description( "Microservicio destinado a cursos" )
            .version( "1.0" )
            .contact( 
                new Contact(
                    "Ricardo Manuel Pelaez Limpi", 
                    "https://github.com/IRypS",
                    "75105685@certus.edu.pe" ) )
            .license("")
            .licenseUrl("")
            .build();
    }

}
