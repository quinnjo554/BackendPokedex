//package com.example.pokemonBackend.Security
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.web.SecurityFilterChain
//
//
//@Configuration
//@EnableWebSecurity
//class WebConfig {
//
//    val ADMIN = "admin"
//    val USER = "user"
//
//    @Bean
//    fun jwtAuthConverter(): JwtAuthConverter {
//        val properties = JwtAuthConverterProperties() // Adjust this based on your configuration
//        return JwtAuthConverter(properties)
//    }
//
//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http.authorizeHttpRequests()
//                .requestMatchers("/").permitAll()
//                .anyRequest().authenticated()
//
//        http.oauth2ResourceServer().jwt()
//                .jwtAuthenticationConverter(jwtAuthConverter())
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//        return http.build()
//    }
//}
//
//
