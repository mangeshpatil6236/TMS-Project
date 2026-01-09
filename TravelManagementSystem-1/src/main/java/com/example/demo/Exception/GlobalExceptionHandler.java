package com.example.demo.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ IdNotFound.class })
	public ResponseEntity<?> idNotFoundHandler(IdNotFound e) {
		return ResponseEntity.status(300).body(e.getMessage());
	}

	@ExceptionHandler({ NameNotFound.class })
	public ResponseEntity<?> nameNotfoundHandler(NameNotFound e) {
		return ResponseEntity.status(301).body(e.getMessage());
	}

	@ExceptionHandler({ RoleNotFound.class })
	public ResponseEntity<?> roleNotFoundHandler(RoleNotFound e) {
		return ResponseEntity.status(302).body(e.getMessage());
	}

	@ExceptionHandler({ EmailNotFound.class })
	public ResponseEntity<?> emailNotfoundHandler(EmailNotFound e) {
		return ResponseEntity.status(303).body(e.getMessage());
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler({ BrandNotFound.class })
	public ResponseEntity<?> brandNotFoundHandler(BrandNotFound e) {
		return ResponseEntity.status(303).body(e.getMessage());
	}

	@ExceptionHandler({ VehicleAlreadyExists.class })
	public ResponseEntity<?> vehicleAlreadyExists(VehicleAlreadyExists e) {
		return ResponseEntity.status(309).body(e.getMessage());
	}
}
