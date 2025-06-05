package ru.application.bff.srssbff.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.Base64;

@Getter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokensData {
    Integer accessTokenDuration;
    Integer refreshTokenDuration;
    String accessToken;
    String idToken;
    String refreshToken;
    JSONObject payload;

    public void reload(
            ResponseEntity<String> response
    ) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode root = mapper.readTree(response.getBody());

            accessToken = root.get("access_token").asText();
            idToken = root.get("id_token").asText();
            refreshToken = root.get("refresh_token").asText();
            accessTokenDuration = root.get("expires_in").asInt();
            refreshTokenDuration = root.get("refresh_expires_in").asInt();

            String payloadPart = idToken.split("\\.")[1];
            String payloadStr = new String(Base64.getUrlDecoder().decode(payloadPart));
            payload = new JSONObject(payloadStr);

        } catch (JsonProcessingException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
