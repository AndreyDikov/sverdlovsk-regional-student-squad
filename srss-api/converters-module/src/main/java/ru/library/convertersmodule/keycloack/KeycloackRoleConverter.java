package ru.library.convertersmodule.keycloack;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import ru.library.convertersmodule.keycloack.impl.KeycloackRoleConverterImpl;

import java.util.Collection;

public interface KeycloackRoleConverter extends Converter<Jwt, Collection<GrantedAuthority>> {
    Collection<GrantedAuthority> convert(Jwt jwt);

    static KeycloackRoleConverter of() {
        return new KeycloackRoleConverterImpl();
    }
}
