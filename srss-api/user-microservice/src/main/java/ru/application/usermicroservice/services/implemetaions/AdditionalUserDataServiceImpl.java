package ru.application.usermicroservice.services.implemetaions;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.usermicroservice.domain.AdditionalUserData;
import ru.application.usermicroservice.mappers.AdditionalUserDataMapper;
import ru.application.usermicroservice.repositories.AdditionalUserDataRepository;
import ru.application.usermicroservice.services.AdditionalUserDataService;
import ru.library.entitiesmodule.entities.user.AdditionalUserDataEntity;
import ru.library.entitiesmodule.entities.user.enums.Gender;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdditionalUserDataServiceImpl implements AdditionalUserDataService {

    AdditionalUserDataRepository userRepository;

    AdditionalUserDataMapper userDataMapper;


    @Override
    public AdditionalUserData read(
            UUID userUuid
    ) {
        AdditionalUserDataEntity userDataEntity = userRepository
                .findByUserUuid(userUuid)
                .orElseGet(() -> userRepository.save(defaultAdditionalUserData(userUuid)));

        return userDataMapper.toDomain(userDataEntity);
    }


    @Override
    public List<AdditionalUserData> readBySquadUuid(
            UUID squadUuid
    ) {
        List<AdditionalUserDataEntity> entities = userRepository.findBySquadUuid(squadUuid);

        return entities.stream()
                .map(userDataMapper::toDomain)
                .toList();
    }


    private static AdditionalUserDataEntity defaultAdditionalUserData(UUID userUuid) {
        return AdditionalUserDataEntity.builder()
                .userUuid(userUuid)
                .gender(Gender.MALE)
                .score(0.0)
                .build();
    }
}
