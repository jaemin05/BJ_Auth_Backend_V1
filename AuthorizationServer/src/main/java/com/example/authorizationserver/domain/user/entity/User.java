package com.example.authorizationserver.domain.user.entity;

import com.example.authorizationserver.domain.user.api.dto.request.SignupRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private UserStates userStates;

    public User(SignupRequest signupRequest){
        this.username = signupRequest.getUsername();
        this.password = signupRequest.getPassword();
        this.nickname = signupRequest.getNickname();
    }
}
