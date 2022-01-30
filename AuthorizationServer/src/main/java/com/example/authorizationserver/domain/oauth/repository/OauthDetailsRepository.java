package com.example.authorizationserver.domain.oauth.repository;

import com.example.authorizationserver.domain.oauth.entity.OauthDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthDetailsRepository extends JpaRepository<OauthDetails, String> {
}
