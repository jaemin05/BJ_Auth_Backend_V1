package com.example.authorizationserver.domain.refreshtoken.repository;

import com.example.authorizationserver.domain.refreshtoken.entiry.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
