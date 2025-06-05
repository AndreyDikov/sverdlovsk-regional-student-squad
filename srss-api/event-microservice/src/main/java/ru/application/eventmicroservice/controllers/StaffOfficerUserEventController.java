package ru.application.eventmicroservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.application.eventmicroservice.domain.UserEvent;
import ru.application.eventmicroservice.mappers.UserEventMapper;
import ru.application.eventmicroservice.services.StaffOfficerUserEventService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staff-officer/user-event")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffOfficerUserEventController {

    StaffOfficerUserEventService staffOfficerUserEventService;

    UserEventMapper userEventMapper;


    @PutMapping("/set-user-score")
    public ResponseEntity<?> setUserScore(
            @RequestParam("user-event-uuid") UUID userEventUuid,
            @RequestParam("event-weight-uuid") UUID eventWeightUuid,
            @RequestParam(defaultValue = "1") Integer score
    ) {
        UserEvent userEvent = staffOfficerUserEventService.setUserScore(
                userEventUuid,
                eventWeightUuid,
                score
        );

        return ResponseEntity.status(HttpStatus.OK).body(userEventMapper.toDto(userEvent));
    }
}
