package com.example.demo.Exception;

public class MobileAlreadyExists extends RuntimeException{
	public MobileAlreadyExists(String msg) {
		super(msg);
	}

}
