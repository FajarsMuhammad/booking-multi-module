package com.kinumz.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(AbstractHttpConfigurer::disable)
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/actuator/**").authenticated()
//                .requestMatchers("/camunda/api/**").authenticated()
//                .anyRequest().permitAll()
//            )
//            .httpBasic(Customizer.withDefaults());
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/health", "/actuator/info", "/actuator/prometheus")
                .permitAll()
                .requestMatchers("/engine-rest/**", "/camunda/**").permitAll()
                .requestMatchers("/api/**").authenticated()
                // Everything else permitted
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
