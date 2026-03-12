package com.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Articulo extends DomainEntity {
	
	@NotBlank
	private String titulo;
	@NotBlank
	private String descripcion;
	@NotBlank
	private String fecha;
	
	@OneToMany
	private List<Comentario> Comentarios;

	public List<Comentario> getComentarios() {
		return Comentarios;
	}

	public void setComentarios(List<Comentario> Comentarios) {
		this.Comentarios = Comentarios;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
}
