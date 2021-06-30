package com.figueiras.photocontest.backend.rest.controllers;

import org.springframework.stereotype.Component;

public interface JwtGenerator {

    String generate(JwtInfo info);

    JwtInfo getInfo(String token);

}
