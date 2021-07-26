package com.figueiras.photocontest.backend.rest.common;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGeneratorImpl implements JwtGenerator {

    @Value("${project.jwt.signKey}")
    private String signKey;

    @Value("${project.jwt.expirationMinutes}")
    private long expirationMinutes;

    @Override
    public String generate(JwtInfo info) {

        return Jwts.builder()
                .claim("userId", info.getIdUsuario())
                .claim("role", info.getRolUsuario())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMinutes*60*1000))
                .signWith(SignatureAlgorithm.HS512, signKey.getBytes())
                .compact();

    }

    @Override
    public JwtInfo getInfo(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(signKey.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return new JwtInfo(
                ((Integer) claims.get("userId")).longValue(),
                claims.getSubject(),
                (String) claims.get("role"));

    }

    @Override
    public String generateForPassword(JwtInfo info) {
        return Jwts.builder()
                .claim("userId", info.getIdUsuario())
                .signWith(SignatureAlgorithm.HS512, signKey.getBytes())
                .compact();
    }

    @Override
    public JwtInfo getInfoForPassword(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return new JwtInfo(
                ((Integer) claims.get("userId")).longValue());
    }
}