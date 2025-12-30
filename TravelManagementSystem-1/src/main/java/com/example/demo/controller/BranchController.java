package com.example.demo.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Branch;
import com.example.demo.model.Status;
import com.example.demo.model.User;
import com.example.demo.repository.BranchRepository;
import com.example.demo.service.BranchService;

import jakarta.persistence.OneToMany;

@RestController
public class BranchController {

	@Autowired
	private BranchService branchservice;

	@Autowired
	private BranchRepository br;

	@PostMapping("addbranch")
	public String add(@RequestBody Branch branch, Principal principal) {
		String name = principal.getName();
		branch.setCreatedBy(name);
		branch.setCreatedAt(new Date());
		branch.setStatus(Status.ACTIVE);

		branchservice.addBranch(branch);
		return "Branch add Successfully..";
	}

	@GetMapping("displaybranch")
	public List<Branch> display() {
		return branchservice.displayBranch();
	}

	@PutMapping("updateBranch/{id}")
	public Branch upadateBranch(@PathVariable Long id, @RequestBody Branch branch) {
		return branchservice.updateBranch(id, branch);
	}

	@DeleteMapping("deletebranch/{id}")
	public String deleteBranch(@PathVariable Long id) {
		branchservice.deleteBranch(id);
		return "Branch Deleted Successfully..";
	}

	@PostMapping("findbybranchid/{id}")
	public ResponseEntity<?> searchByBranchId(@PathVariable Long id) {
		Branch result = branchservice.findByBranchId(id);
		return new ResponseEntity<Branch>(result, HttpStatus.OK);
	}

	// Branch Pagination
	@GetMapping("branch/page")
	public Page<Branch> getAllBranches(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size) {
		Sort sort = Sort.by("id").ascending();
		Pageable of = PageRequest.of(page, size, sort);
		return this.br.findAll(of);
	}
	
	
	
}
