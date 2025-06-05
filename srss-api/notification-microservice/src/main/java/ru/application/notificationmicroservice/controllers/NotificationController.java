package ru.application.notificationmicroservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.application.notificationmicroservice.domain.Notification;
import ru.application.notificationmicroservice.dto.NotificationDto;
import ru.application.notificationmicroservice.mappers.NotificationMapper;
import ru.application.notificationmicroservice.services.NotificationService;

import java.util.List;
import java.util.UUID;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationController {

    NotificationService notificationService;

    NotificationMapper notificationMapper;


    @GetMapping
    public ResponseEntity<?> getAllForCurrentUser(
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID userUuid = UUID.fromString(jwt.getSubject());
        log.info("Запрос всех уведомлений для пользователя {}", userUuid);

        List<Notification> notifications = notificationService.getNotificationsForUser(userUuid);
        List<NotificationDto> dtoList = notificationMapper.toDtoList(notifications);

        return ResponseEntity.ok(dtoList);
    }
}
