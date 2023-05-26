package com.example.pokemonBackend.Security
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig {
    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val corsConfig = CorsConfiguration()
        corsConfig.allowedOrigins = listOf("http://localhost:5173/") // Add your whitelist of allowed origins
        corsConfig.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS") // Add your allowed HTTP methods
        corsConfig.allowCredentials = true // Set this to true if you want to allow credentials (e.g., cookies) to be included in CORS requests
        source.registerCorsConfiguration("/**", corsConfig)
        return CorsFilter(source)
    }
}