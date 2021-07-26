package com.figueiras.photocontest.backend.rest.common;

public interface JwtGenerator {

    String generate(JwtInfo info);

    JwtInfo getInfo(String token);

    String generateForPassword(JwtInfo info);

    JwtInfo getInfoForPassword(String token);

}
