package com.example.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long reg_number;
	private String brand;
	private String model;
	private String color;
	private String manufactureYear;
	private String engineNo;
	private String chesisNo;
	@Enumerated(EnumType.STRING)
	private FuelType fuelType;
	private String ownerName;
	private Date reg_date;
	
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch;
	
	@OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private RcBook rcBook;
}
