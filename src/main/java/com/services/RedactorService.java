package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Redactor;
import com.repository.RedactorRepository;

@Service
public class RedactorService {
	@Autowired
	private RedactorRepository redactorRepository;

	public List<Redactor> findAll() {
		return redactorRepository.findAll();
	}

	public Optional<Redactor> findOne(int id) {
		return redactorRepository.findById(id);
	}
	
	public Optional<Redactor> findByUsername(String nombre){
		return redactorRepository.findByUsername(nombre);
	}

	public Redactor save(Redactor a) {
		return redactorRepository.save(a);
	}

	public Redactor update(Redactor a, int id) {
		if (findOne(id).isPresent()) {
			Redactor redactorBd = findOne(id).get();
			redactorBd.setNombre(a.getNombre());
			redactorBd.setPassword(a.getPassword());
			redactorBd.setApellido1(a.getApellido1());
			redactorBd.setApellido1(a.getApellido2());
			redactorBd.setCorreo(a.getCorreo());
			redactorBd.setTelefono(a.getTelefono());

			return redactorRepository.save(redactorBd);
		} else {
			return null;
		}

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
