package com.example.authorizationserver.domain.user.service;

import com.example.authorizationserver.domain.user.api.dto.request.SignupRequest;

public interface UserService {
    void signup(SignupRequest signupRequest);
}
