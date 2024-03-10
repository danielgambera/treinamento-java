package br.com.ehmf.AppProdutos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		
		return new OpenAPI() 
			.components(
					new Components().addSecuritySchemes("basicScheme",
							new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))
					).info(new Info()
							.title("Treinamento Java")
							.description("Treinamento Java")
							.contact(new Contact()
								.name("Djobi Djoba")
								.email("abel.lebel@muaramicintra.com")
								.url("https://www.youtube.com/watch?v=oxNK7VBizac")
								).version("Versão 0.0.1-SNAPSHOT")
							);

		}
}
