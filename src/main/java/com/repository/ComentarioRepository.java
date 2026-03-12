package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Integer> {
	
}
