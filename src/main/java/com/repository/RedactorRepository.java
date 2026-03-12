package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Redactor;

@Repository
public interface RedactorRepository extends JpaRepository<Redactor, Integer>{
	
}
