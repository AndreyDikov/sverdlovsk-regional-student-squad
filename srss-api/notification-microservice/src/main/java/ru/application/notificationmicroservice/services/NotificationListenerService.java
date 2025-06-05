package ru.application.notificationmicroservice.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import ru.application.notificationmicroservice.domain.UserEventMessage;

public interface NotificationListenerService {
    void listen(ConsumerRecord<String, UserEventMessage> record);
}
