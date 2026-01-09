package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Vehicle;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;

@RestController
public class VehicleController {
	
	@Autowired
	private VehicleRepository vehicleRepo;

	@Autowired
	private VehicleService vehicleService;
	
	@PostMapping("addVehicle")
	public String addVehicle(@RequestBody Vehicle vehicle) {
		vehicleService.addVehicle(vehicle);
		return "Vehicle Added Succesfully";
	}
	
	@GetMapping("displayVehicle")
	public List<Vehicle> displayVehicle(){
		return vehicleService.displayVehicles();
	}
	
	@PutMapping("updateVehicle/{id}")
	public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle v) {
		return vehicleService.updateVehicle(id, v);
	}
	
	@DeleteMapping("deleteVehicle/{id}")
	public String deleteVehicle(@PathVariable Long id) {
		vehicleService.deleteVehicle(id);
		return "Vehicle Deleted Successfully";
	}
	
	@PostMapping("search/{brand}")
	public ResponseEntity<?> searchByBrand(@PathVariable String brand){
		Vehicle result = vehicleService.searchByBrand(brand);
		return new ResponseEntity<Vehicle>(result, HttpStatus.OK);
	}
	
	//vehicle pagination
	@GetMapping("vehicle/page")
	public Page<Vehicle> getAllVehicles(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2")int size){
		Sort sort = Sort.by("id").ascending();
		Pageable of = PageRequest.of(page, size, sort);
		return this.vehicleRepo.findAll(of);
	}
}
