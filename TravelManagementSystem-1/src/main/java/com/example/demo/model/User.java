package com.example.demo.model;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message="Name Cannot be Empty !")
	private String name;
	@Email(message="Invalid Email !")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email end with @gmail.com.")
	@NotBlank
	private String email;
	@NotBlank
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%&+=!]).{8,}$", message = "Password must be at least 8 characters and includes one uppercase, lowercase, number and special symbol")
	private String password;
	@NotBlank(message="Address cannot be Empty !")
	private String address;
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "INVALID MOBILE NUMBER")
	@NotBlank(message="Mobile Cannot be Empty !")
	private String mobile;
	private String role;
	private String status;
	private String profileUrl;
	
	@OneToOne(mappedBy = "user")
	@JsonIgnore
	private Document document;
	
	@ManyToOne
	private Branch branch;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub

		GrantedAuthority authority = new SimpleGrantedAuthority(this.role);
		return List.of(authority);

	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	
}
