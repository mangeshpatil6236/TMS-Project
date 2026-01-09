package com.example.demo.repository;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByRole(String role);

	boolean existsByName(String name);

	User findByRole(String role);

	User findByName(String name);

	boolean existsByEmail(String email);

	User findByEmail(String email);

	List<User> findByBranch_Title(String title);

	boolean existsByMobile(String mobile);

	 

}
