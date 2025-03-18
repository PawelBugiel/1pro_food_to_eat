package com.pawelbugiel.foodtoeat.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Pawel Bugiel",
                        url = "https://github.com/PawelBugiel"
                ),
                description = "A training purposes application.",
                title = "Food to eat",
                version = "1.0 - SNAPSHOT"
        )
//        ),
//        servers = {
//                @Server(
//                        description = "Test ENV",
//                        url = "http://localhost:8081"
//                )
//        }
)
class OpenApiConfig {

}
