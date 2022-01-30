package com.example.authorizationserver.domain.oauth.repository;

import com.example.authorizationserver.domain.oauth.entity.OauthCode;
import org.springframework.data.repository.CrudRepository;

public interface OauthCodeRepository extends CrudRepository<OauthCode, String> {
}
