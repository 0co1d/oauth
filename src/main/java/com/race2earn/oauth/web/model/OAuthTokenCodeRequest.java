package com.race2earn.oauth.web.model;

import lombok.Data;

@Data
public class OAuthTokenCodeRequest {
    private Integer appId;
    private String appSecret;
    private String code;
}
