package com.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Visitante extends Actor {
	
	@OneToMany
	private List<Comentario> Comentarios;

	public List<Comentario> getComentarios() {
		return Comentarios;
	}

	public void setComentarios(List<Comentario> Comentarios) {
		this.Comentarios = Comentarios;
	}
	
	
}
