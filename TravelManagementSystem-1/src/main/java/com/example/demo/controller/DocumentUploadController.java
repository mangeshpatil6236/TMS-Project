package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.FileService;

@RestController
@RequestMapping("/upload")
public class DocumentUploadController {

	@Autowired
	private FileService fileservice;

	@PostMapping
	public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("File is Empty");
		}

		String message = fileservice.uploadFile(file);
		return ResponseEntity.ok(message);
	}

	@GetMapping(value = "/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getFile(@PathVariable String filename) {
		byte[] file = this.fileservice.getFile(filename);
		return file;
	}
	
	@DeleteMapping("/{filename}")
	public ResponseEntity<String> deletFile(@PathVariable String filename){
		String message = this.fileservice.deleteFile(filename);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
