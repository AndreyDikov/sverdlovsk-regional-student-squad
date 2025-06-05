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
import ru.application.eventmicroservice.services.StaffCommanderEventWeightService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staff-commander/event-weight")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffCommanderEventWeightController {

    StaffCommanderEventWeightService staffCommanderEventWeightService;


    @PutMapping("/set-weight-by-uuid")
    public ResponseEntity<?> setWeightByUuid(
            @RequestParam UUID uuid,
            @RequestParam Double weight
    ) {
        staffCommanderEventWeightService.setWeightByUuid(uuid, weight);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
