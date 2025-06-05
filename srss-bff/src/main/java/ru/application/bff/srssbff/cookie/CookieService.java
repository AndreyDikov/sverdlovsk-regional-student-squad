package ru.application.bff.srssbff.cookie;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import ru.application.bff.srssbff.enums.CookieKey;

public interface CookieService {
    HttpHeaders deleteCookies();
    HttpHeaders createCookies();
}
