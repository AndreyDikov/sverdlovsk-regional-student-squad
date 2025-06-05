package ru.application.servermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServerMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerMicroserviceApplication.class, args);
    }

}
