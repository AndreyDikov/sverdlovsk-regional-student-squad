package ru.application.eventmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(basePackages = "ru.library.entitiesmodule.entities.event")
public class EventMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventMicroserviceApplication.class, args);
    }

}
