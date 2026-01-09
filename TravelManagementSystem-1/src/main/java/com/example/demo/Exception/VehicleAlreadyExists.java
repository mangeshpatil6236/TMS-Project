package com.example.demo.Exception;

public class VehicleAlreadyExists extends RuntimeException {
	public VehicleAlreadyExists(String msg) {
		super(msg);
	}
}
