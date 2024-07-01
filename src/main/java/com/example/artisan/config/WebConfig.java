package com.example.artisan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
        	
        	/*
        	 * registry.addMapping("/**")：允許所有路徑的請求。
        	 * allowedOrigins("http://localhost:3000")：只允許來自 http://localhost:3000 的請求。
        	 * allowedMethods("GET", "POST", "PUT", "DELETE")：允許這些 HTTP 方法。
        	 * allowedHeaders("*")：允許所有標頭。
        	 * allowCredentials(true)：允許憑證（如 Cookies）。
        	 * maxAge(3600)：預檢請求的緩存時間，以秒為單位。 
        	 */
        	
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "http://192.168.100.179:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
