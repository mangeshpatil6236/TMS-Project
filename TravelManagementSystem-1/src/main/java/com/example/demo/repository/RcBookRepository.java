package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.RcBook;

public interface RcBookRepository extends JpaRepository<RcBook, Long>{

}
