package com.example.authorizationserver.domain.user.service;

import com.example.authorizationserver.domain.user.api.dto.request.SignupRequest;
import com.example.authorizationserver.domain.user.entity.User;
import com.example.authorizationserver.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserFacade userFacade;

    @Transactional
    @Override
    public void signup(SignupRequest signupRequest) {
        User user = userFacade.registerUser(signupRequest);
    }
}
