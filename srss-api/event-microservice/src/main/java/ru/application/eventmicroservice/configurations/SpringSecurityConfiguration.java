package ru.application.eventmicroservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import ru.library.convertersmodule.keycloack.KeycloackRoleConverter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(KeycloackRoleConverter.of());

        http.csrf(AbstractHttpConfigurer::disable) // Отключение CSRF с использованием нового API
                .authorizeHttpRequests(auth -> auth // Разрешение доступа к ресурсам
                        // любая из этих ролей сможет обращаться к /fighter/**
                        .requestMatchers("/fighter/**")
                        .hasAnyRole(
                                "fighter",
                                "squad-commander",
                                "staff-officer",
                                "staff-commander"
                        )

                        // только squad-commander и выше
                        .requestMatchers("/squad-commander/**")
                        .hasAnyRole(
                                "squad-commander",
                                "staff-officer",
                                "staff-commander"
                        )

                        // только staff-officer и выше
                        .requestMatchers("/staff-officer/**")
                        .hasAnyRole(
                                "staff-officer",
                                "staff-commander"
                        )

                        // только staff-commander
                        .requestMatchers("/staff-commander/**").hasRole("staff-commander")
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2 // Настройка OAuth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)) // Настройка JWT
                );

        return http.build();
    }
}
