package com.example.authorizationserver.global.security.jwt;

import com.example.authorizationserver.domain.refreshtoken.entiry.RefreshToken;
import com.example.authorizationserver.domain.refreshtoken.repository.RefreshTokenRepository;
import com.example.authorizationserver.global.exception.ExpiredTokenException;
import com.example.authorizationserver.global.exception.InvalidTokenException;
import com.example.authorizationserver.global.security.auth.CustomUserDetailsService;
import com.example.authorizationserver.global.security.jwt.JwtProperties;
import com.example.authorizationserver.global.security.jwt.dto.response.TokenResponse;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.util.annotation.Nullable;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse generateToken(String username){
        String access = generateAccessToken(username);
        String refresh = generateRefreshToken(username);

        refreshTokenRepository.save(
                new RefreshToken(
                        username, refresh
                )
        );
        return new TokenResponse(access, refresh);
    }

    public String generateAccessToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .claim("type", "access")
                .signWith(SignatureAlgorithm.ES256, jwtProperties.getSecretKey())
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtProperties.getAccessExp() * 1000)
                )
                .setIssuedAt(new Date())
                .compact();
    }

    public String generateRefreshToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .claim("type", "refresh")
                .signWith(SignatureAlgorithm.ES256, jwtProperties.getSecretKey())
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtProperties.getRefreshExp() * 1000)
                )
                .setIssuedAt(new Date())
                .compact();
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(jwtProperties.getHeader());
        if(bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix()) && bearerToken.length() > 7) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Authentication getAuthentication(String token){
        Claims body = getBody(token);

        if(body.getExpiration().before(new Date())){
            throw ExpiredTokenException.EXCEPTION;
        }

        UserDetails userDetails = getDetails(body);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Claims getBody(String token){
        try{
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ExpiredJwtException e){
            throw ExpiredTokenException.EXCEPTION;
        }catch (MalformedJwtException | SignatureException e){
            //MalformedJwtException : 구조적인 문제가 있는 JWT인 경우
            throw InvalidTokenException.EXCEPTION;
        }
    }

    @Nullable
    public UserDetails getDetails(Claims body){
        return customUserDetailsService.loadUserByUsername(body.getSubject());
    }

}
