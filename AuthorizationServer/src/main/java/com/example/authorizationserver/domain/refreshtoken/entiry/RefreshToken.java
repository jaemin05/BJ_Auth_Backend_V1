package com.example.authorizationserver.domain.refreshtoken.entiry;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class RefreshToken {
    @Id
    private String username;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long ttl;

    public RefreshToken(String username, String refreshToken){
        this.username = username;
        this.refreshToken = refreshToken;
        this.ttl = 300L;
    }
}
