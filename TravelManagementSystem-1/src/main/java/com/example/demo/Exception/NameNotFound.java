package com.example.demo.Exception;

public class NameNotFound extends RuntimeException{
	
	public NameNotFound(String msg) {
		super(msg);
	}

}
