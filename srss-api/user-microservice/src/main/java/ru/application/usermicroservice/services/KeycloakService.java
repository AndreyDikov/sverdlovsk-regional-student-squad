package ru.application.usermicroservice.services;

import ru.application.usermicroservice.domain.User;

import java.util.List;
import java.util.UUID;

public interface KeycloakService {
    User findUserByUuid(UUID uuid);
    List<User> findAllUsers();
}
