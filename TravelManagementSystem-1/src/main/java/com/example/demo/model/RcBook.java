package com.example.demo.model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RcBook {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date issue_date;
	private Date expiry_date;
	private String rcBook_imgUrl;
	
	@OneToOne
	@JoinColumn(name = "vehicle_id", nullable = false)
	private Vehicle vehicle;
}
