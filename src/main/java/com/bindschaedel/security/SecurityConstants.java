package com.bindschaedel.security;

public class SecurityConstants {
    //TODO generate proper secret
    public static final String SECRET          = "TmpSecretToken";
    public static final long   EXPIRATION_TIME = 12 * 24 * 60 * 60 * 1000; //10 days
    public static final String TOKEN_PREFIX    = "Bearer ";
    public static final String HEADER_STRING   = "Authorization";
    public static final String SIGN_UP_URL     = "/user/sign-up";
}
