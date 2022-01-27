package com.example.authorizationserver.global.security.auth;

import com.example.authorizationserver.domain.user.entity.User;
import com.example.authorizationserver.domain.user.exception.UserNotFoundException;
import com.example.authorizationserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetails(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION)
        );
    }
}
