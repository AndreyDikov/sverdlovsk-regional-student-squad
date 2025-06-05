package ru.application.usermicroservice.utils;

import ru.application.usermicroservice.enums.Roles;

import java.util.List;
import java.util.Set;

public interface RoleSelector {
    List<Roles> PRIORITY = List.of(
            Roles.STAFF_COMMANDER,
            Roles.STAFF_OFFICER,
            Roles.SQUAD_COMMANDER,
            Roles.FIGHTER
    );


    static Roles selectRole(Set<Roles> realmRoles) {
        return PRIORITY.stream()
                .filter(realmRoles::contains)
                .findFirst()
                .orElse(Roles.FIGHTER);
    }
}
