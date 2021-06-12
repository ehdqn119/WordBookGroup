
package com.example.wordbook.Configuraiton;


import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import java.util.Arrays;
import java.util.List;

@Component
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
    Info info = new Info().title("Today tem").version(appVersion)
            .description("Today tem")
            .termsOfService("http://swagger.io/terms/")
            .contact(new Contact().name("Lee Sang Min").url("https://blog.jiniworld.me/").email("ehdqn119@gmail.com"))
            .license(new License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));


    /*List<Server> servers = Arrays.asList(new Server().url("localhost:8928").description("demo ()"));*/


    /*SecurityScheme securityScheme = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER).name("Authorization");
    SecurityRequirement schemaRequirement = new SecurityRequirement().addList("bearerAuth");

*/

    return new OpenAPI()
            /*.components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
            .security(Arrays.asList(schemaRequirement))
            .servers(servers)*/
            .info(info);
  }

}