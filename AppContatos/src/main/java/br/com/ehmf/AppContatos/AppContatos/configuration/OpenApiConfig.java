package br.com.ehmf.AppContatos.AppContatos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		
		final String securitySchemeName = "bearerAuth";
		 
		return new OpenAPI() 
			.components(
					new Components()
							.addSecuritySchemes(securitySchemeName,
									new SecurityScheme()
											.type(SecurityScheme.Type.HTTP)
											.scheme("bearer")
											.bearerFormat("JWT")
											.in(SecurityScheme.In.HEADER)
											.name("Authorization"))
					)
					.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
					.info(new Info()
							.title("Treinamento Java")
							.description("Avaliação Java - 12/03/2024")
							.contact(new Contact()
								.name("Rick Roll")
								.email("abel.lebel@muaramicintra.com")
								.url("https://www.youtube.com/watch?v=oxNK7VBizac")
								).version("Versão 0.0.1-SNAPSHOT"));
						

		}
}
