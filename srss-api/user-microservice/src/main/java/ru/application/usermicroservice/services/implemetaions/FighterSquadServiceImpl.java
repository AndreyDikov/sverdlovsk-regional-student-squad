package ru.application.usermicroservice.services.implemetaions;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.usermicroservice.repositories.AdditionalUserDataRepository;
import ru.application.usermicroservice.services.FighterSquadService;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FighterSquadServiceImpl implements FighterSquadService {

    AdditionalUserDataRepository userRepository;

    @Override
    public void leaveSquad(UUID userUuid) {
        userRepository.deleteSquad(userUuid);
    }
}
