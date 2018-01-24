package com.race2earn.oauth.web;

import com.race2earn.oauth.domain.User;
import com.race2earn.oauth.domain.UserToken;
import com.race2earn.oauth.exception.InvalidTokenException;
import com.race2earn.oauth.exception.TokenExpireException;
import com.race2earn.oauth.exception.UserCredentialsException;
import com.race2earn.oauth.service.UserTokenService;
import com.race2earn.oauth.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/token")
public class UserTokenRestController {

    private final UserTokenService userTokenService;

    @Autowired
    public UserTokenRestController(UserTokenService userTokenService) {
        this.userTokenService = userTokenService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public UserTokenResponse createToken(@Valid @RequestBody UserTokenInitialRequest tokenRequest) throws UserCredentialsException {
        UserToken userToken = this.userTokenService.create(tokenRequest);
        return new UserTokenResponse(userToken);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteToken(@Valid @RequestBody UserTokenRequest userTokenRequest) {
        userTokenService.delete(userTokenRequest);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public UserResponse checkToken(@Valid @RequestBody UserTokenRequest userTokenRequest) throws TokenExpireException, InvalidTokenException {
        User user = this.userTokenService.checkAccessToken(userTokenRequest);
        return new UserResponse(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public UserTokenResponse refreshToken(@Valid @RequestBody UserTokenRefreshRequest refreshTokenRequest)
            throws TokenExpireException, InvalidTokenException {
        UserToken userToken = this.userTokenService.refresh(refreshTokenRequest);
        return new UserTokenResponse(userToken);
    }
}
