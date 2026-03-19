package com.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.entity.Articulo;
import com.entity.Redactor;
import com.entity.Rol;
import com.repository.RedactorRepository;
import com.security.JWTUtils;

@Service
public class RedactorService {
	@Autowired
	private RedactorRepository redactorRepository;
	@Autowired
	private PasswordEncoder pEncode;
	public List<Redactor> findAll() {
		return redactorRepository.findAll();
	}

	public Optional<Redactor> findOne(int id) {
		return redactorRepository.findById(id);
	}
	
	public Optional<Redactor> findByUsername(String nombre){
		return redactorRepository.findByUsername(nombre);
	}
	
	public Redactor saveCreate(Redactor a) {
		a.setPassword(pEncode.encode(a.getPassword()));
		a.setRol(Rol.REDACTOR);
		a.setArticulos(new ArrayList<Articulo>());
		return redactorRepository.save(a);
	}
	
	public Redactor save(Redactor a) {
		return redactorRepository.save(a);
	}


	public Boolean delete(int id) {
		if (findOne(id).isPresent()) {
			redactorRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}
