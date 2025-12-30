 package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

	void addUser(User u);

	List<User> displayUser();

	User updateUser(Long id, User u);

	void deleteUser(Long id);

	User searchById(Long id);

	User searchByRole(String role);

	User searchByName(String name);

	User searchByEmail(String email);

	List<User> searchUserByBranch(String title);

	 

}
