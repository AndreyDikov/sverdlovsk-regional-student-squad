package ru.application.usermicroservice.configurations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RealmResourceConfiguration {

    final Keycloak keycloak;

    @Value("${keycloak.realm}")
    String realm;


    @Bean
    public RealmResource realmResource() {
        return keycloak.realm(realm);
    }
}
