package com.example.demo.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message="Title cannot be Empty !")
	private String title;
	@NotBlank(message = "Location cannot be Empty !")
	private String location;
	private Date createdAt;
	private String createdBy;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@JsonIgnore
	@OneToMany(mappedBy = "branch")
	private List<User> user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "brand")
	private List<Vehicle> vehicle;
}
