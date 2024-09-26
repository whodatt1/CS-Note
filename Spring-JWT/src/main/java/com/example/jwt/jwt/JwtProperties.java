package com.example.jwt.jwt;

public interface JwtProperties {
	String SECRET = "kim";
	int EXPIRATION_TIME = 30;
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}
