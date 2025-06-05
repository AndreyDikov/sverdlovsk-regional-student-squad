package ru.application.notificationmicroservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.application.notificationmicroservice.domain.UserEventMessage;
import ru.application.notificationmicroservice.enums.UserEventAction;
import ru.application.notificationmicroservice.repositories.NotificationEventRepository;
import ru.application.notificationmicroservice.repositories.NotificationRepository;
import ru.application.notificationmicroservice.services.NotificationListenerService;
import ru.library.entitiesmodule.entities.notification.NotificationEntity;
import ru.library.entitiesmodule.entities.notification.NotificationEventEntity;
import ru.library.entitiesmodule.entities.notification.enums.NotificationType;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationListenerServiceImpl implements NotificationListenerService {

    NotificationRepository notificationRepository;
    NotificationEventRepository notificationEventRepository;


    @Override
    @KafkaListener(topics = "user-event-topic", containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, UserEventMessage> record) {
        NotificationEntity notification = NotificationEntity.builder()
                .toUserUuid(record.value().getUserUuid())
                .fromUserUuid(null)
                .type(record.value().getAction() == UserEventAction.SUBSCRIBE
                        ? NotificationType.SIGN_UP_FOR_EVENT
                        : NotificationType.RATE_EVENT)
                .createdAt(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);
        notificationEventRepository.save(
                NotificationEventEntity.builder()
                        .notificationUuid(notification.getUuid())
                        .eventUuid(record.value().getEventUuid())
                        .build()
        );
    }
}
