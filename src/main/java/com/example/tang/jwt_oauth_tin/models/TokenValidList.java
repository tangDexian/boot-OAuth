package com.example.tang.jwt_oauth_tin.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class TokenValidList {


    @Id
    private String jti;

    private Long userId;

    private Long expires;

    private Boolean isBlackListed;

    public TokenValidList(String jti, Long userId, Long expires) {
        this.jti = jti;
        this.userId = userId;
        this.expires = expires;
    }
}
