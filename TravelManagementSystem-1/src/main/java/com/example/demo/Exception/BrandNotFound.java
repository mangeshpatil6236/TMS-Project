package com.example.demo.Exception;

public class BrandNotFound extends RuntimeException{
	
	public BrandNotFound(String msg) {
		super(msg);
	}
}
