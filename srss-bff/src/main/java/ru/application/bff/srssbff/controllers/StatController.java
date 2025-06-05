package ru.application.bff.srssbff.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.application.bff.srssbff.dto.OperationDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stat")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatController {

    RestTemplate restTemplate;


    // для получения статистики - отдельный метод
    // перенаправляет запрос в Resource Server и добавляет в него access token
    @PostMapping
    public ResponseEntity<?> stat(
            @RequestBody OperationDto operationDto,
            @RequestBody String email,
            @CookieValue("AT") String accessToken
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<?> request = new HttpEntity<>(email, headers);

        return restTemplate.postForEntity(
                operationDto.url() + "/stat",
                request,
                Object.class
        );
    }
}
