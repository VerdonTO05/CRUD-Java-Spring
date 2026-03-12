package com.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Redactor extends Actor {

	@OneToMany
	private List<Articulo> Articulos;

	public List<Articulo> getArticulos() {
		return Articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		Articulos = articulos;
	}
}
