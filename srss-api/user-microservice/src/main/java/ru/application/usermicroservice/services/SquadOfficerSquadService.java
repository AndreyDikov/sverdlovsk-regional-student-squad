package ru.application.usermicroservice.services;

import ru.application.usermicroservice.domain.Squad;

public interface SquadOfficerSquadService {
    Squad create(Squad squad);
    Squad update(Squad squad);
}
