package es.madc.billing.config;

import java.util.Collections;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
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
public class AppConfig {
    
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.setContextPath("/api");
    }
    
    @Bean
    public Docket api() {                
        return new Docket(DocumentationType.SWAGGER_2)          
          .select()
          .apis(RequestHandlerSelectors.basePackage("es.madc.billing.controller"))
          .paths(PathSelectors.ant("/billing/*"))
          .build()
          .apiInfo(apiInfo());
    }
     
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "My Sales Taxes REST API", 
          "This is an example of billing sales taxes API.", 
          "API TOS", 
          "Terms of service", 
          new Contact("Miguel Ángel Díaz Calvo", "http://madc-billing.herokuapp.com/api/", "miguelangeldiazcalvo@gmail.com"), 
          "Mit License", "https://opensource.org/licenses/MIT", Collections.emptyList());
    }
    
}