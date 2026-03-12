package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Actor;
import com.repository.ActorRepository;

@Service
public class ActorService {
	@Autowired
	private ActorRepository actorRepository;
	
	public List<Actor> findAll() {
		return actorRepository.findAll();
	}
	
	public Optional<Actor> findOne(int id) {
		return actorRepository.findById(id);
	}
	
	public Actor save(Actor a) {
		return actorRepository.save(a);
	}
	
	public Actor update(Actor a, int id) {
		if(findOne(id).isPresent()) {
			Actor actorbd = findOne(id).get();
			actorbd.setNombre(a.getNombre());
			actorbd.setPassword(a.getPassword());
			actorbd.setApellido1(a.getApellido1());
			actorbd.setApellido1(a.getApellido2());
			actorbd.setCorreo(a.getCorreo());
			actorbd.setTelefono(a.getTelefono());
			
			return actorRepository.save(actorbd);
		}else {
			return null;
		}
		
	}
	
	public Boolean delete(int id) {
		if(findOne(id).isPresent()) {
			actorRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
}
