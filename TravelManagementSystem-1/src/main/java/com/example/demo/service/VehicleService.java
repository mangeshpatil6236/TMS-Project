package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Vehicle;

public interface VehicleService {

	void addVehicle(Vehicle vehicle);

	List<Vehicle> displayVehicles();

	Vehicle updateVehicle(Long id, Vehicle v);

	void deleteVehicle(Long id);

	Vehicle searchByBrand(String brand);

}
