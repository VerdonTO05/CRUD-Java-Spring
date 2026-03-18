package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Articulo;
import com.entity.Redactor;

@Repository
public interface RedactorRepository extends JpaRepository<Redactor, Integer>{

	Optional<Redactor> findByUsername(String nombre);
	
}
