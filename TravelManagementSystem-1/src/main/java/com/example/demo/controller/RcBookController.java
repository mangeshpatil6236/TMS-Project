package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Exception.IdNotFound;
import com.example.demo.Exception.VehicleAlreadyExists;
import com.example.demo.model.RcBook;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.RcBookRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rcBook")
@RequiredArgsConstructor
public class RcBookController {

	private final RcBookRepository rcBookRepo;
	private final FileService fileService;
	private final VehicleRepository vehicleRepo;

	@PostMapping("/{vehicleid}")
	public ResponseEntity<Map<String, String>> RcBookUpload(@PathVariable Long vehicleid,
			@RequestPart("rcBookInfo") RcBook rcBook, @RequestPart("rcBookDoc") MultipartFile file) {

		if (vehicleRepo.existsById(vehicleid)) {
			throw new VehicleAlreadyExists("Vehicle Already Exists !");
		}

		Vehicle vehicle = this.vehicleRepo.findById(vehicleid)
				.orElseThrow(() -> new IdNotFound("Vehicle Not Found " + vehicleid + " !"));
		rcBook.setRcBook_imgUrl(this.fileService.uploadFile(file));
		rcBook.setVehicle(vehicle);
		this.rcBookRepo.save(rcBook);

		Map<String, String> response = new HashMap<>();
		response.put("message", "RcBook Upload Successfully...");
		response.put("status", "true");

		return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
	};

	@GetMapping("/{rcBookId}")
	public RcBook getRcBookByID(@PathVariable Long rcBookId) {
		return rcBookRepo.findById(rcBookId).orElseThrow(() -> new IdNotFound("RcBook Not Found 👎"));
	}

	@GetMapping
	public List<RcBook> getAllRcBooks() {
		return rcBookRepo.findAll();
	}
}
