package ru.application.eventmicroservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.application.eventmicroservice.domain.UserEvent;
import ru.application.eventmicroservice.dto.UserEventMessageDto;
import ru.application.eventmicroservice.enums.Topics;
import ru.application.eventmicroservice.enums.UserEventAction;
import ru.application.eventmicroservice.mappers.UserEventMapper;
import ru.application.eventmicroservice.repositories.UserEventRepository;
import ru.application.eventmicroservice.services.SquadCommanderUserEventService;
import ru.library.entitiesmodule.entities.event.UserEventEntity;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SquadCommanderUserEventServiceImpl implements SquadCommanderUserEventService {

    KafkaTemplate<String, Object> kafkaTemplate;

    UserEventRepository userEventRepository;

    UserEventMapper userEventMapper;


    @Override
    public UserEvent subscribeUserToEvent(
            UUID eventUuid,
            UUID userUuid
    ) {
        UserEventEntity userEventEntity = userEventRepository.save(
                UserEventEntity.builder()
                        .userUuid(userUuid)
                        .eventUuid(eventUuid)
                        .eventRating(0)
                        .build()
        );

        kafkaTemplate.send(
                Topics.USER_EVENT.getTopicName(),
                new UserEventMessageDto(eventUuid, userUuid, UserEventAction.SUBSCRIBE)
        );

        return userEventMapper.toDomain(userEventEntity);
    }


    @Override
    public void unsubscribeUserFromEvent(UUID eventUuid, UUID userUuid) {
        UserEventEntity userEventEntity = userEventRepository.findByUserUuidAndEventUuid(
                eventUuid,
                userUuid
        );

        userEventRepository.deleteById(userEventEntity.getUuid());

        kafkaTemplate.send(
                Topics.USER_EVENT.getTopicName(),
                new UserEventMessageDto(eventUuid, userUuid, UserEventAction.UNSUBSCRIBE)
        );
    }
}
