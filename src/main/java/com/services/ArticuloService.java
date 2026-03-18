package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Articulo;
import com.repository.ArticuloRepository;
@Service
public class ArticuloService {
	@Autowired
	private ArticuloRepository articuloRepository;
	
	
	public List<Articulo> findAll(){
		return articuloRepository.findAll();
	}
	
	public Optional<Articulo> findOne(int id){
		return articuloRepository.findById(id);
	}
	
	
	
	public Articulo save(Articulo a) {
		return articuloRepository.save(a);
	}
	
	public Articulo update(int id, Articulo a) {
		if(findOne(id).isPresent()) {
			Articulo articuloBd = findOne(id).get();
			articuloBd.setTitulo(a.getTitulo());
			articuloBd.setDescripcion(a.getDescripcion());
			articuloBd.setFecha(a.getFecha());
			return articuloRepository.save(articuloBd);
		}else {
			return null;
		}
	}
	
	
	public Boolean delete(int id) {
		if(findOne(id).isPresent()) {
			articuloRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
		
	}
}
