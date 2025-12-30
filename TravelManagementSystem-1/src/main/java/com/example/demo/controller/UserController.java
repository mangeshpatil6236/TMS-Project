package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FileService;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FileService fileService;

	@Autowired
	private UserService us;

	@PostMapping("add")
	public ResponseEntity<?> add(@RequestPart("user") User u, @RequestPart("file") MultipartFile file) {

		try {
			String uploadFile = this.fileService.uploadFile(file);
			System.out.println("upload file name" + uploadFile);

			u.setProfileUrl(uploadFile);

			us.addUser(u);
			return  ResponseEntity.ok("User Added Successfully");
		} catch (RuntimeException e) {
			// TODO: handle exception
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(e.getMessage());
		}
	}

	@GetMapping("display")
	public List<User> display(Principal principal) {
		String name = principal.getName();
		System.out.println(name);
		return us.displayUser();
	}

	@PutMapping("update/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User u) {
		return us.updateUser(id, u);
	}

	@DeleteMapping("delete/{id}")
	public String delete(@PathVariable Long id) {

		User user = us.searchById(id);

		if (user.getProfileUrl() != null && !user.getProfileUrl().isEmpty()) {
			fileService.deleteFile(user.getProfileUrl());
		}

		us.deleteUser(id);
		return "User Deleted Successfully";
	}

	@PostMapping("findbyid/{id}")
	public ResponseEntity<?> searchById(@PathVariable Long id) {
		User result = us.searchById(id);
		return new ResponseEntity<User>(result, HttpStatus.OK);
	}

	@PostMapping("findbyrole/{role}")
	public ResponseEntity<?> searchByRole(@PathVariable String role) {
		User result = us.searchByRole(role);
		return new ResponseEntity<User>(result, HttpStatus.OK);
	}

	@PostMapping("findbyname/{name}")
	public ResponseEntity<?> searchByName(@PathVariable String name) {
		User result = us.searchByName(name);
		return new ResponseEntity<User>(result, HttpStatus.OK);
	}

	// in this method we find current user login
	@PostMapping("profile")
	public ResponseEntity<?> searchByEmail(Principal principal) {
		String name = principal.getName();
		User result = us.searchByEmail(name);
		return new ResponseEntity<User>(result, HttpStatus.OK);
	}

	// User Pagination
	@GetMapping("user/page")
	public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2") int size) {
		Sort sort = Sort.by("id").ascending();
		Pageable of = PageRequest.of(page, size, sort);
		return this.userRepository.findAll(of);
	}

	@GetMapping(value = "profilepicture", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] userProfilePicture(Principal principal) {
		String name = principal.getName();

		User user = this.us.searchByEmail(name);
		return this.fileService.getFile(user.getProfileUrl());
	}

//	@GetMapping("profile")
//	public ResponseEntity<User> findCurrentLoginUser(Principal principal) {
//		String name = principal.getName();
//		this.us.searchByEmail(name)
//;	}

	
	@GetMapping("finduserbybranch/{title}")
	public List<User> searchUserByBranch(@PathVariable String title){
		return us.searchUserByBranch(title);
	}
	
}
