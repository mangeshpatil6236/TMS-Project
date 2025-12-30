package com.example.demo.Exception;

public class UserAlreadyExists extends RuntimeException{
	public UserAlreadyExists (String msg) {
		super(msg);
	}

}
