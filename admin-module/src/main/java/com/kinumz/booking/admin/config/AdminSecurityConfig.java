package com.kinumz.booking.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AdminSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF because Camunda has its own protection
            .csrf(AbstractHttpConfigurer::disable)

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                // ✅ Allow Camunda webapps (Cockpit, Tasklist, Admin) to handle login internally
                .requestMatchers("/camunda/**").permitAll()
                // ✅ Secure actuator endpoints if present
                .requestMatchers("/actuator/**").authenticated()
                // Everything else allowed
                .anyRequest().permitAll()
            );
        return http.build();
    }
}
