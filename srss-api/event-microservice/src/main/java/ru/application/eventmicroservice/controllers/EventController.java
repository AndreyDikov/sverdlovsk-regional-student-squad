package ru.application.eventmicroservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.application.eventmicroservice.domain.Event;
import ru.application.eventmicroservice.domain.UserEvent;
import ru.application.eventmicroservice.dto.EventDto;
import ru.application.eventmicroservice.enums.EventsSortedType;
import ru.application.eventmicroservice.mappers.EventMapper;
import ru.application.eventmicroservice.services.EventService;
import ru.application.eventmicroservice.services.UserEventService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventController {

    EventService eventService;
    UserEventService userEventService;

    EventMapper eventMapper;


    @GetMapping("/read")
    public ResponseEntity<?> read(
            @RequestParam UUID uuid
    ) {
        Event event = eventService.read(uuid);

        return ResponseEntity.status(HttpStatus.OK).body(eventMapper.toDto(event));
    }


    @GetMapping("/read-user-events")
    public ResponseEntity<?> readUserEvents(
            @RequestParam("user-uuid") UUID userUuid
    ) {
        List<UserEvent> userEvents = userEventService.readUserEventsByUserUuid(userUuid);
        List<EventDto> events = userEvents.stream()
                .map(ue -> eventService.read(ue.getEventUuid()))
                .map(eventMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(events);
    }


    @GetMapping("/read-events-by-author-uuid")
    public ResponseEntity<?> readEventsByAuthorUuid(
            @RequestParam("author-uuid") UUID authorUuid
    ) {
        List<Event> events = eventService.readEventsByAuthorUuid(authorUuid);

        List<EventDto> eventsDto = events.stream()
                .map(eventMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(eventsDto);
    }


    @GetMapping("/read-user-events-history")
    public ResponseEntity<?> readUserEventsHistory(
            @RequestParam("user-uuid") UUID uuid
    ) {
        List<Event> events = eventService.readUserEventsHistory(uuid);

        List<EventDto> eventsDto = events.stream()
                .map(eventMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(eventsDto);
    }


    @GetMapping("/filter-events")
    public ResponseEntity<?> filter(
            @RequestParam(name = "category-uuid", required = false) UUID categoryUuid,
            @RequestParam("sorted-type") EventsSortedType sortedType,
            @RequestParam int size,
            @RequestParam int page
    ) {
        List<Event> events = eventService.filter(categoryUuid, sortedType, size, page);

        List<EventDto> result = events.stream()
                .map(eventMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @GetMapping("/history-events")
    public ResponseEntity<?> history(
            @RequestParam(name = "category-uuid", required = false) UUID categoryUuid,
            @RequestParam("sorted-type") EventsSortedType sortedType,
            @RequestParam int size,
            @RequestParam int page
    ) {
        List<Event> events = eventService.history(categoryUuid, sortedType, size, page);

        List<EventDto> result = events.stream()
                .map(eventMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
