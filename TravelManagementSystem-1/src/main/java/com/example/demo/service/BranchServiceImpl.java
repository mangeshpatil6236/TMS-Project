package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.IdNotFound;
import com.example.demo.model.Branch;
import com.example.demo.model.User;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.UserRepository;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchRepository branchrepository;
	

	@Override
	public void addBranch(Branch branch) {
		// TODO Auto-generated method stub
		branchrepository.save(branch);
	}

	@Override
	public List<Branch> displayBranch() {
		// TODO Auto-generated method stub
		
		return branchrepository.findAll();
	}

	@Override
	public void deleteBranch(Long id) {
		// TODO Auto-generated method stub
		branchrepository.deleteById(id);
	}

	@Override
	public Branch updateBranch(Long id, Branch branch) {
		// TODO Auto-generated method stub
		branch.setId(id);
		return branchrepository.save(branch);
	}

	@Override
	public Branch findByBranchId(Long id) {
		// TODO Auto-generated method stub
		if (!branchrepository.existsById(id)) {
			throw new IdNotFound("ID NOT FOUND !!");
		}

		return branchrepository.findById(id).get();
	}

}
