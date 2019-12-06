package br.com.cadastropaciente.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Classe de configuração para o Swagger 2.
 * 
 * @author Felipe Tadeu
 *
 */
@Configuration
public class SwaggerConfig {
	@Bean
	Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.cadastropaciente"))
				.paths(PathSelectors.any())
				.build();
	}
}
