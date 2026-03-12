package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo,Integer> {

}
