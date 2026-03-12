package com.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Actor extends DomainEntity {
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String apellido1;
	
	
	private String apellido2;
	
	@NotBlank
	@Pattern(regexp="\\w[@]{1}\\w[.]{1}\\w")
	private String correo;
	
	@NotBlank
	@Pattern(regexp= "[6-9]\\d{8}")
	private String telefono;

	
	private  Rol Rol;
	
	@OneToMany
	private List<PerfilSocial> perfilesSociales;
	
	
	
	
	
	//Contructor Vacio//
	public Actor() {

	}

	public Actor(String username, String password, String nombre, String apellido1, String apellido2, String correo,
			String telefono) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.correo = correo;
		this.telefono = telefono;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Rol getRol() {
		return Rol;
	}

	public void setRol(Rol rol) {
		Rol = rol;
	}

}
