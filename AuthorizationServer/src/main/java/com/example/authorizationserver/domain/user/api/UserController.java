package com.example.authorizationserver.domain.user.api;

import com.example.authorizationserver.domain.user.api.dto.request.SignupRequest;
import com.example.authorizationserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestPart @Valid SignupRequest signupRequest){
        userService.signup(signupRequest);
    }
}
