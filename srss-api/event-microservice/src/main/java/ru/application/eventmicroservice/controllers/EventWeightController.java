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
import ru.application.eventmicroservice.domain.EventWeight;
import ru.application.eventmicroservice.dto.EventWeightDto;
import ru.application.eventmicroservice.mappers.EventWeightMapper;
import ru.application.eventmicroservice.services.EventWeightService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event-weight")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventWeightController {

    EventWeightService eventWeightService;

    EventWeightMapper eventWeightMapper;


    @GetMapping("/read-weights-by-category-evaluation-method-uuid")
    public ResponseEntity<?> readWeightsByCategoryEvaluationMethodUuid(
            @RequestParam("category-evaluation-method-uuid") UUID categoryEvaluationMethodUuid
    ) {
        List<EventWeight> eventWeights = eventWeightService
                .readWeightsByCategoryEvaluationMethodUuid(categoryEvaluationMethodUuid);

        List<EventWeightDto> eventWeightsDto = eventWeights.stream()
                .map(eventWeightMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(eventWeightsDto);
    }


    @GetMapping("/read-weights-by-category-uuid")
    public ResponseEntity<?> readWeightsByCategoryUuid(
            @RequestParam("category-uuid") UUID categoryUuid
    ) {
        List<EventWeight> eventWeights = eventWeightService
                .readWeightsByCategoryUuid(categoryUuid);

        List<EventWeightDto> eventWeightsDto = eventWeights.stream()
                .map(eventWeightMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(eventWeightsDto);
    }
}
