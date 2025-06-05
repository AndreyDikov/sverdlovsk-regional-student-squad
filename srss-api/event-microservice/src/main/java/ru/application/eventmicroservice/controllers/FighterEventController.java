package ru.application.eventmicroservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.application.eventmicroservice.domain.Event;
import ru.application.eventmicroservice.domain.UserEvent;
import ru.application.eventmicroservice.dto.EventDto;
import ru.application.eventmicroservice.mappers.EventMapper;
import ru.application.eventmicroservice.mappers.UserEventMapper;
import ru.application.eventmicroservice.services.FighterEventService;

import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/fighter/events")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FighterEventController {

    FighterEventService fighterEventService;

    UserEventMapper userEventMapper;
    EventMapper eventMapper;


    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody EventDto eventDto
    ) {
        UUID userUuid = UUID.fromString(jwt.getSubject());
        Event event = eventMapper.toDomain(eventDto);

        log.info("Пользователь {} записался на событие {}", userUuid, event.getUuid());

        UserEvent userEvent = fighterEventService.subscribe(userUuid, event);

        return ResponseEntity.status(HttpStatus.CREATED).body(userEventMapper.toDto(userEvent));
    }


    @DeleteMapping("/unsubscribe")
    public ResponseEntity<?> unsubscribe(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam UUID userEventUuid
    ) {
        UUID userUuid = UUID.fromString(jwt.getSubject());

        log.info("Пользователь {} удалил запись в таблице users_events {}", userUuid, userEventUuid);

        fighterEventService.unsubscribe(userUuid, userEventUuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
