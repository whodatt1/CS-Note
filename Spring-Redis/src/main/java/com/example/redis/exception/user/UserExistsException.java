package com.example.redis.exception.user;

public class UserExistsException extends RuntimeException {
	
	public UserExistsException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public UserExistsException(String msg) {
		super(msg);
	}
}
