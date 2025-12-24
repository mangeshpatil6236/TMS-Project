package com.example.demo.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({IdNotFound.class})
	public ResponseEntity<?> idNotFoundHandler(IdNotFound e){
		return ResponseEntity.status(300).body(e.getMessage());
	}
	
	@ExceptionHandler({NameNotFound.class})
	public ResponseEntity<?> nameNotfoundHandler(NameNotFound e){
		return ResponseEntity.status(301).body(e.getMessage());
	}
	
	@ExceptionHandler({RoleNotFound.class})
	public ResponseEntity<?> roleNotFoundHandler(RoleNotFound e){
		return ResponseEntity.status(302).body(e.getMessage());
	}
	
	@ExceptionHandler({EmailNotFound.class})
	public ResponseEntity<?> emailNotfoundHandler(EmailNotFound e){
		return ResponseEntity.status(303).body(e.getMessage());
	}

}
