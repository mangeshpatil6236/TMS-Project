 package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.EmailNotFound;
import com.example.demo.Exception.IdNotFound;
import com.example.demo.Exception.NameNotFound;
import com.example.demo.Exception.RoleNotFound;
import com.example.demo.model.User;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository ur;
	
	@Autowired
	private PasswordEncoder passeEncoder;
	
	@Autowired
	private BranchRepository branchRepo;
	
	@Override
	public void addUser(User u) {
		// TODO Auto-generated method stub
		
		if(ur.existsByEmail(u.getEmail())) {
			throw new RuntimeException("User Already Exists With this Email...!");
		}
		
		if(ur.existsByMobile(u.getMobile())) {
			throw new RuntimeException("User Already Exists with this Mobile Number");
		}
		 
		u.setPassword(passeEncoder.encode(u.getPassword()));
		ur.save(u);
	}

	@Override
	public List<User> displayUser() {
		// TODO Auto-generated method stub
		return ur.findAll();
	}

	@Override
	public User updateUser(Long id, User u) {
		// TODO Auto-generated method stub
		u.setId(id);
		return ur.save(u);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		ur.deleteById(id);
	}

	@Override
	public User searchById(Long id) {
		// TODO Auto-generated method stub
		if(!ur.existsById(id)) {
			throw new IdNotFound("ID NOT FOUND !!");
		}
		return ur.findById(id).get();
	}

	@Override
	public User searchByRole(String role) {
		// TODO Auto-generated method stub
		if(!ur.existsByRole(role)) {
			throw new RoleNotFound("ROLE NOT FOUND !!");
		}
		return ur.findByRole(role);
	}

	@Override
	public User searchByName(String name) {
		// TODO Auto-generated method stub
		if(!ur.existsByName(name)) {
			throw new NameNotFound("NAME NOT FOUND !!");
		}
		return ur.findByName(name);
	}

	@Override
	public User searchByEmail(String email) {
		// TODO Auto-generated method stub
		if(!ur.existsByEmail(email)) {
			throw new EmailNotFound("EMAIL NOT FOUND !!");
		}
		return ur.findByEmail(email);
	}

	@Override
	public List<User> searchUserByBranch(String title) {
		// TODO Auto-generated method stub
		List<User> users = ur.findByBranch_Title(title);
		
		if(users.isEmpty()) {
			 throw new NameNotFound("No Users Found with this Branch"+title);
		}
		return users;
	}

	
}
