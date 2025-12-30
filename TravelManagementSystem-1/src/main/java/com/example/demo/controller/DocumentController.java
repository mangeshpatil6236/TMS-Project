package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Document;
import com.example.demo.model.User;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("document")
@RequiredArgsConstructor

public class DocumentController {

	private final DocumentRepository documentRepo;
	private final FileService fileservice;
	private final UserRepository userRepo;

	@PostMapping("/{userid}")
	public ResponseEntity<Map<String, String>> DocumentUpload(@PathVariable Long userid,
			@RequestPart("document") Document document, @RequestPart("addhar") MultipartFile file,
			@RequestPart("pancard") MultipartFile file2) {
		
		User user = this.userRepo.findById(userid).orElseThrow(() ->new RuntimeException("User Not Found"+userid));
		
		document.setAdharImgUrl(this.fileservice.uploadFile(file));
		document.setPanImgUrl(this.fileservice.uploadFile(file2));
		document.setUser(user);
		this.documentRepo.save(document);
		Map<String, String> response = new HashMap<>();
		response.put("message", "document upload successfully...");
		response.put( "status", "true");
		
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.OK);
	};
}
