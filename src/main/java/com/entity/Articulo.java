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
	private String desc;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
}
