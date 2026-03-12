package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Visitante;
@Repository
public interface VisitantesRepository extends JpaRepository<Visitante,Integer>{

}
