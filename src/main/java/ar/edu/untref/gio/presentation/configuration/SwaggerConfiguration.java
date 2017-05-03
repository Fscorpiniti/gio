package ar.edu.untref.gio.presentation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String TITLE = "Gio";
    private static final String DESCRIPTION = "Gestor de inversiones online";
    private static final String VERSION = "1";
    private static final String EMPTY_STRING = "";
    private static final String NAME_CONTACT = "Fernando";
    private static final String EMAIL_CONTACT = "gio@gmail.com";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(TITLE, DESCRIPTION, VERSION, EMPTY_STRING, buildContact(), EMPTY_STRING, EMPTY_STRING);
    }

    private Contact buildContact() {
        return new Contact(NAME_CONTACT, EMPTY_STRING, EMAIL_CONTACT);
    }

}