package com.example.authorizationserver.domain.user.facade;

import com.example.authorizationserver.domain.user.api.dto.request.SignupRequest;
import com.example.authorizationserver.domain.user.entity.User;
import com.example.authorizationserver.domain.user.exception.UserAlreadyExistsException;
import com.example.authorizationserver.domain.user.exception.UserNotFoundException;
import com.example.authorizationserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(SignupRequest signupRequest){
        isAlreadyExists(signupRequest.getUsername());
        return userRepository.save(
                new User(
                        signupRequest.encodePassword(
                                passwordEncoder.encode(signupRequest.getPassword())
                        )
                )
        );
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void isAlreadyExists(String username){
        if(userRepository.findByUsername(username).isPresent()){
            throw UserAlreadyExistsException.EXCEPTION;
        }
    }
}
