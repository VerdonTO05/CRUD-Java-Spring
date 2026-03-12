package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Comentario;
import com.repository.ComentarioRepository;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;

	public List<Comentario> findAll(){
		return comentarioRepository.findAll();
	}
	
	public Optional<Comentario> findOne(int id) {
		return comentarioRepository.findById(id);
	}
	
	public Comentario save(Comentario c) {
		return comentarioRepository.save(c);
	}
	
	public Comentario update(int id,Comentario c) {
		if(findOne(id).isPresent()) {
			Comentario comBd = findOne(id).get();
			comBd.setTexto(c.getTexto());
			return comentarioRepository.save(comBd);
		}else {
			return null;
		}
		
	}
	
	public Boolean delete(int id) {
		if(findOne(id).isPresent()) {
			comentarioRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
}
