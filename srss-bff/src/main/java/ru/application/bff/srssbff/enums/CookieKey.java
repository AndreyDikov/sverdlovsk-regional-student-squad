package ru.application.bff.srssbff.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CookieKey {

    ID_TOKEN_COOKIE_KEY("IT"),
    REFRESH_TOKEN_COOKIE_KEY("RT"),
    ACCESS_TOKEN_COOKIE_KEY("AT");

    String key;
}
