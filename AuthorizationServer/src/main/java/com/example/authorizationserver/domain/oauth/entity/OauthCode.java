package com.example.authorizationserver.domain.oauth.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class OauthCode {
    @Id
    private String clientId;

    private String code;

    private String username;

    private Long ttl;

    public OauthCode(String clientId, String code, String username){
        this.clientId = clientId;
        this.code = code;
        this.username = username;
        this.ttl = 180L;
    }
}
