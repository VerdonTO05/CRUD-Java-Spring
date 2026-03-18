package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.entity.Rol;
import com.entity.Visitante;
import com.repository.VisitantesRepository;

@Service
public class VisitanteService {
	@Autowired
	private VisitantesRepository visitantesRepository;
	@Autowired
	private PasswordEncoder passwordEnconder;

	public List<Visitante> findAll() {
		return visitantesRepository.findAll();
	}

	public Optional<Visitante> findOne(int id) {
		return visitantesRepository.findById(id);
	}
	
	public Optional<Visitante> findByUsername(String nombre){
		return visitantesRepository.findByUsername(nombre);
	}

	public Visitante save(Visitante a) {
		a.setPassword(passwordEnconder.encode(a.getPassword()));
		a.setRol(Rol.VISITANTE);
		return visitantesRepository.save(a);
	}

	public Visitante update(Visitante a, int id) {
		if (findOne(id).isPresent()) {
			Visitante visitanteBd = findOne(id).get();
			visitanteBd.setNombre(a.getNombre());
			visitanteBd.setPassword(a.getPassword());
			visitanteBd.setApellido1(a.getApellido1());
			visitanteBd.setApellido1(a.getApellido2());
			visitanteBd.setCorreo(a.getCorreo());
			visitanteBd.setTelefono(a.getTelefono());

			return visitantesRepository.save(visitanteBd);
		} else {
			return null;
		}

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
