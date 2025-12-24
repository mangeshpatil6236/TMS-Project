package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Branch;

public interface BranchService {

	void addBranch(Branch branch);

	List<Branch> displayBranch();

	void deleteBranch(Long id);

	Branch updateBranch(Long id, Branch branch);

	Branch findByBranchId(Long id);

}
