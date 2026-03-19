package com.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.entity.Comentario;
import com.entity.Rol;
import com.entity.Visitante;
import com.repository.VisitantesRepository;
import com.security.JWTUtils;

@Service
public class VisitanteService {
	@Autowired
	private VisitantesRepository visitantesRepository;
	@Autowired
	private PasswordEncoder passwordEnconder;
	@Autowired
	private JWTUtils JWTUtils;

	@Autowired
	private PasswordEncoder pEncode;

	public List<Visitante> findAll() {
		return visitantesRepository.findAll();
	}

	public Optional<Visitante> findOne(int id) {
		return visitantesRepository.findById(id);
	}

	public Optional<Visitante> findByUsername(String nombre) {
		return visitantesRepository.findByUsername(nombre);
	}

	public Visitante saveCreate(Visitante v) {
		v.setPassword(pEncode.encode(v.getPassword()));
		v.setRol(Rol.VISITANTE);
		v.setComentarios(new ArrayList<Comentario>());
		return visitantesRepository.save(v);
	}

	public Visitante save(Visitante a) {
		return visitantesRepository.save(a);
	}


	public Boolean delete(int id) {
		if (findOne(id).isPresent()) {
			visitantesRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
