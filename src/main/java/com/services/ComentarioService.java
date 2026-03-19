package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Articulo;
import com.entity.Comentario;
import com.entity.Visitante;
import com.repository.ComentarioRepository;
import com.security.JWTUtils;

import jakarta.transaction.Transactional;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;
	@Autowired
	private ArticuloService articuloService;
	@Autowired
	private VisitanteService visitanteService;
	@Autowired
	private JWTUtils JWTUtils;

	public List<Comentario> findAll(){
		return comentarioRepository.findAll();
	}
	
	public Optional<Comentario> findOne(int id) {
		return comentarioRepository.findById(id);
	}
	@Transactional
	public Comentario save(Comentario c, int id) {
		Optional<Articulo> c1 = articuloService.findOne(id);
		Visitante v1 = JWTUtils.userLogin();
		if(c1.isPresent()) {
			Comentario comentariobd = comentarioRepository.save(c);
			c1.get().getComentarios().add(comentariobd);
			v1.getComentarios().add(comentariobd);
			visitanteService.save(v1);
			articuloService.save(c1.get());
		}
		return comentarioRepository.save(c);
	}
	
	
	@Transactional
	public Comentario update(int id, Comentario a) {
		Visitante r1 = JWTUtils.userLogin();
		if (findOne(id).isPresent() && r1.getComentarios().contains(a)) {
			Comentario comentarioBd = findOne(id).get();
			comentarioBd.setTexto(a.getTexto());
			return comentarioRepository.save(comentarioBd);
		} else {
			return null;
		}
	}
	
	@Transactional
	public int delete(int id) {
		Visitante r1 = JWTUtils.userLogin();
		Optional<Comentario> a1 = findOne(id);
		if (a1.isPresent()) {
			if (r1.getComentarios().contains(a1.get())) {
				r1.getComentarios().remove(a1.get());
				visitanteService.save(r1);
				comentarioRepository.deleteById(id);
				return 0;
			} else {
				return 2;
			}
		} else {
			return 1;
		}

	}
}
