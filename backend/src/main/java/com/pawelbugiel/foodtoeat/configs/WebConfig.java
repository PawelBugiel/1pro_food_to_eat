package com.pawelbugiel.foodtoeat.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
*
* CORS (Cross-Origin Resource Sharing) config to run with frontend

Jeśli Twój frontend próbuje wysyłać zapytania do backendu, który działa na innym porcie (w tym przypadku 8081), musisz upewnić się, że backend ma skonfigurowane odpowiednie nagłówki CORS, aby zezwolić na żądania z frontendu.
*
* */



@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080") // Dodaj adres frontend
                .allowedOrigins("http://192.168.0.172:8080") // Dodaj adres frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
