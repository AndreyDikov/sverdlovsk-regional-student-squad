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
import ru.application.eventmicroservice.services.StaffCommanderCategoryService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staff-commander/category")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffCommanderCategoryController {

    StaffCommanderCategoryService staffCommanderCategoryService;


    @PutMapping("/set-users-coefficient-by-category-uuid")
    public ResponseEntity<?> setUsersCoefficientByCategoryUuid(
            @RequestParam("category-uuid") UUID categoryUuid,
            @RequestParam("coefficient") Double coefficient
    ) {
        staffCommanderCategoryService.setUsersCoefficientByCategoryUuid(
                categoryUuid,
                coefficient
        );

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }


    @PutMapping("/set-squads-coefficient-by-category-uuid")
    public ResponseEntity<?> setSquadsCoefficientByCategoryUuid(
            @RequestParam("category-uuid") UUID categoryUuid,
            @RequestParam("coefficient") Double coefficient
    ) {
        staffCommanderCategoryService.setSquadsCoefficientByCategoryUuid(
                categoryUuid,
                coefficient
        );

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
