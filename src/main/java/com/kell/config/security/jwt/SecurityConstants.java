package com.kell.config.security.jwt;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 86400000;
    public static final String JWT_SECRET = "secret";

    public static final long REFRESH_TOKEN_EXPIRATION = 30L * 86400000;

}