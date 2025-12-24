package com.example.demo.Exception;

public class EmailNotFound extends RuntimeException{
	
	public EmailNotFound(String msg) {
		super(msg);
	}

}
