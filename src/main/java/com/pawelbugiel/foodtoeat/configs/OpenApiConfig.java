package com.pawelbugiel.foodtoeat.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Pawel Bugiel - PCtm",
                        url = "https://github.com/PawelBugiel"
                ),
                description = " OpenApi documentation to learn how to create a documentation using Swagger",
                title = "Food to eat application, for training purposes - Paweł Bugiel",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "license url"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8081"
                ),
                @Server(
                        description = "Prod ENV",
                        url = "http://some-target-serwer"
                )
        }
)
class OpenApiConfig {

}
