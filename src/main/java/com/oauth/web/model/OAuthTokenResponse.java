package com.oauth.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oauth.domain.OAuthToken;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OAuthTokenResponse {
    private String accessToken;
    private Date accessExpires;
    private String refreshToken;
    private Date refreshExpires;

    public OAuthTokenResponse(OAuthToken oAuthToken) {
        this.accessToken = oAuthToken.getAccessToken();
        this.accessExpires = oAuthToken.getAccessExpires();
        this.refreshToken = oAuthToken.getRefreshToken();
        this.refreshExpires = oAuthToken.getRefreshExpires();
    }
}
