package ru.application.bff.srssbff.cookie.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import ru.application.bff.srssbff.cookie.CookieService;
import ru.application.bff.srssbff.enums.CookieKey;
import ru.application.bff.srssbff.utils.TokensData;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CookieServiceImpl implements CookieService {

    final TokensData tokensData;

    @Value("${cookie.domain}") String domain;


    @Override
    public HttpHeaders createCookies() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.SET_COOKIE, accessTokenCookie().toString());
        responseHeaders.add(HttpHeaders.SET_COOKIE, refreshTokenCookie().toString());
        responseHeaders.add(HttpHeaders.SET_COOKIE, idTokenCookie().toString());

        return responseHeaders;
    }


    @Override
    public HttpHeaders deleteCookies() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.SET_COOKIE, getHeaderByCookie(CookieKey.ACCESS_TOKEN_COOKIE_KEY));
        responseHeaders.add(HttpHeaders.SET_COOKIE, getHeaderByCookie(CookieKey.REFRESH_TOKEN_COOKIE_KEY));
        responseHeaders.add(HttpHeaders.SET_COOKIE, getHeaderByCookie(CookieKey.ID_TOKEN_COOKIE_KEY));

        return responseHeaders;
    }


    private HttpCookie createCookie(
            CookieKey key,
            String value,
            int durationInSeconds
    ) {
        return ResponseCookie.from(key.getKey(), value)
                .maxAge(durationInSeconds)
                .sameSite(SameSiteCookies.NONE.getValue())
                .httpOnly(true)
                .secure(true)
                .domain(domain)
                .path("/")
                .build();
    }


    private HttpCookie deleteCookie(CookieKey key) {
        return ResponseCookie.from(key.getKey(), "")
                .maxAge(0)
                .sameSite(SameSiteCookies.NONE.getValue())
                .httpOnly(true)
                .secure(true)
                .domain(domain)
                .path("/")
                .build();
    }


    private HttpCookie accessTokenCookie() {
        return createCookie(
                CookieKey.ACCESS_TOKEN_COOKIE_KEY,
                tokensData.getAccessToken(),
                tokensData.getAccessTokenDuration()
        );
    }


    private HttpCookie refreshTokenCookie() {
        return createCookie(
                CookieKey.REFRESH_TOKEN_COOKIE_KEY,
                tokensData.getRefreshToken(),
                tokensData.getRefreshTokenDuration()
        );
    }


    private HttpCookie idTokenCookie() {
        return createCookie(
                CookieKey.ID_TOKEN_COOKIE_KEY,
                tokensData.getIdToken(),
                tokensData.getAccessTokenDuration()
        );
    }


    private String getHeaderByCookie(CookieKey key) {
        return deleteCookie(key).toString();
    }
}
