package ru.application.eventmicroservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.application.eventmicroservice.domain.Event;
import ru.application.eventmicroservice.dto.NewEventDto;
import ru.application.eventmicroservice.mappers.EventMapper;
import ru.application.eventmicroservice.services.StaffOfficerEventService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staff-officer/events")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffOfficerEventController {

    StaffOfficerEventService staffOfficerEventService;

    EventMapper eventMapper;


    @PostMapping("/create")
    public ResponseEntity<?> create(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody NewEventDto newEventDto
    ) {
        UUID userUuid = UUID.fromString(jwt.getSubject());

        Event event = staffOfficerEventService.create(
                userUuid,
                newEventDto.categoryUuid(),
                newEventDto.evaluationMethod(),
                eventMapper.toDomain(newEventDto.eventDto())
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(eventMapper.toDto(event));
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody NewEventDto newEventDto
    ) {
        UUID userUuid = UUID.fromString(jwt.getSubject());

        Event event = staffOfficerEventService.update(
                userUuid,
                newEventDto.categoryUuid(),
                newEventDto.evaluationMethod(),
                eventMapper.toDomain(newEventDto.eventDto())
        );

        return ResponseEntity.status(HttpStatus.OK).body(eventMapper.toDto(event));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEventByUuid(
            @RequestParam("event-uuid") UUID eventUuid
    ) {
        staffOfficerEventService.deleteEventByUuid(eventUuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
