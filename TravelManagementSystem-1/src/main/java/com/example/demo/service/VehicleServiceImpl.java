package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.BrandNotFound;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService{
	
	@Autowired
	private VehicleRepository vehicleRepo;

	@Override
	public void addVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		vehicleRepo.save(vehicle);
	}

	@Override
	public List<Vehicle> displayVehicles() {
		// TODO Auto-generated method stub
		return vehicleRepo.findAll();
	}

	@Override
	public Vehicle updateVehicle(Long id, Vehicle v) {
		// TODO Auto-generated method stub
		v.setId(id);
		return vehicleRepo.save(v);
	}

	@Override
	public void deleteVehicle(Long id) {
		// TODO Auto-generated method stub
		vehicleRepo.deleteById(id);
	}

	@Override
	public Vehicle searchByBrand(String brand) {
		// TODO Auto-generated method stub
		if(!vehicleRepo.existsByBrand(brand)) {
			throw new BrandNotFound("BRAND NOT FOUND !!");
		}
		return vehicleRepo.findByBrand(brand);
	}

}
