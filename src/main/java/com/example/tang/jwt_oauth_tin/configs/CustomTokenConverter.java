package com.example.tang.jwt_oauth_tin.configs;

import com.example.tang.jwt_oauth_tin.models.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {

        if (authentication.getOAuth2Request().getGrantType().equalsIgnoreCase("password")) {
            Account account = (Account) authentication.getPrincipal();

            final Map<String, Object> additionalInfo = new HashMap<>();

            additionalInfo.put("DB_ID", account.getId());
            additionalInfo.put("Email", account.getEmail());

            ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(additionalInfo);
        }

        accessToken = super.enhance(accessToken, authentication);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(new HashMap<>());
        return accessToken;
    }
}
