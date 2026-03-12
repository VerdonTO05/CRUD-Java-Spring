package com.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class PerfilSocial extends DomainEntity {
	@NotBlank
	private String url;
	@NotBlank
	private String nombreUsuario;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public PerfilSocial() {
		super();
	}
	
	public PerfilSocial(@NotBlank String url, @NotBlank String nombreUsuario) {
		super();
		this.url = url;
		this.nombreUsuario = nombreUsuario;
	}
	
	
}
