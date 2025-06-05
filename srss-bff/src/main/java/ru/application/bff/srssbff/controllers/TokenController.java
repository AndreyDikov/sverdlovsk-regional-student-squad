package ru.application.bff.srssbff.controllers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.application.bff.srssbff.cookie.CookieService;
import ru.application.bff.srssbff.utils.TokensData;
import ru.application.bff.srssbff.enums.CookieKey;

@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenController {

    final RestTemplate restTemplate;
    final CookieService cookieService;
    final TokensData tokensData;

    @Value("${keycloak.secret}") String clientSecret;
    @Value("${keycloak.url}") String keyCloakURI;
    @Value("${client.url}") String clientURL;
    @Value("${keycloak.clientid}") String clientId;
    @Value("${keycloak.granttype.code}") String grantTypeCode;
    @Value("${keycloak.granttype.refresh}") String grantTypeRefresh;


    @PostMapping("/all-tokens")
    public ResponseEntity<String> token(
            @RequestBody String code
    ) {
        MultiValueMap<String, String> mapForm = mapForm();

        mapForm.add("grant_type", grantTypeCode);
        mapForm.add("code", code);
        mapForm.add("redirect_uri", clientURL);

        ResponseEntity<String> response = response(mapForm);

        tokensData.reload(response);

        HttpHeaders responseHeaders = cookieService.createCookies();

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .build();
    }


    @GetMapping("/exchange")
    public ResponseEntity<String> exchangeRefreshToken(
            @CookieValue("RT") String oldRefreshToken
    ) {
        MultiValueMap<String, String> mapForm = mapForm();

        mapForm.add("grant_type", grantTypeRefresh);
        mapForm.add("refresh_token", oldRefreshToken);

        ResponseEntity<String> response = response(mapForm);

        tokensData.reload(response);

        HttpHeaders responseHeaders = cookieService.createCookies();

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .build();
    }


    private MultiValueMap<String, String> mapForm() {
        MultiValueMap<String, String> mapForm = new LinkedMultiValueMap<>();

        mapForm.add("client_id", clientId);
        mapForm.add("client_secret", clientSecret);

        return mapForm;
    }


    private HttpEntity<MultiValueMap<String, String>> request(
            MultiValueMap<String, String> mapForm
    ) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return new HttpEntity<>(mapForm, headers);
    }


    private ResponseEntity<String> response(
            MultiValueMap<String, String> mapForm
    ) {
        return restTemplate.exchange(
                keyCloakURI + "/token",
                HttpMethod.POST,
                request(mapForm),
                String.class
        );
    }
}
