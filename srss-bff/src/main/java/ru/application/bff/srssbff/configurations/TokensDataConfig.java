package ru.application.bff.srssbff.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.application.bff.srssbff.utils.TokensData;

@Configuration
public class TokensDataConfig {

    @Bean
    public TokensData tokensData() {
        return TokensData.builder()
                .accessToken("")
                .refreshToken("")
                .idToken("")
                .accessTokenDuration(0)
                .refreshTokenDuration(0)
                .build();
    }
}
