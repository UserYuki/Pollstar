package com.Toine.pollstar.Api.Config;

public class AuthenticationConfigConstants
{
    public static final String SECRET = "Java_to_Dev_Secret";
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/user";
    public static final String VOTER_ACTIONS_URL = "/voter/**";
}