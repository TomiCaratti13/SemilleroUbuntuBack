package com.semillero.ubuntu.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class TokenJwtConfig {
    public final static Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
}
