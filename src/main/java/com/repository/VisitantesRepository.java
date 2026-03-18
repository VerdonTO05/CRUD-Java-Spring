package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Articulo;
import com.entity.Visitante;
@Repository
public interface VisitantesRepository extends JpaRepository<Visitante,Integer>{

	Optional<Visitante> findByUsername(String nombre);

}
