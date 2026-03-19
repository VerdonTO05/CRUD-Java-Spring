package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Articulo;
import com.entity.Redactor;
import com.repository.ArticuloRepository;
import com.security.JWTUtils;

import jakarta.transaction.Transactional;

@Service
public class ArticuloService {
	@Autowired
	private ArticuloRepository articuloRepository;
	@Autowired
	private JWTUtils JWTUtils;
	@Autowired
	private RedactorService redactorService;
	public List<Articulo> findAll() {
		return articuloRepository.findAll();
	}

	public Optional<Articulo> findOne(int id) {
		return articuloRepository.findById(id);
	}

	public Articulo save(Articulo a) {
		return articuloRepository.save(a);
	}
	@Transactional
	public Articulo update(int id, Articulo a) {
		Redactor r1 = JWTUtils.userLogin();
		if (findOne(id).isPresent() && r1.getArticulos().contains(a)) {
			Articulo articuloBd = findOne(id).get();
			articuloBd.setTitulo(a.getTitulo());
			articuloBd.setDescripcion(a.getDescripcion());
			articuloBd.setFecha(a.getFecha());
			return articuloRepository.save(articuloBd);
		} else {
			return null;
		}
	}
	@Transactional
	public int delete(int id) {
		Redactor r1 = JWTUtils.userLogin();
		Optional<Articulo> a1 = findOne(id);
		if (a1.isPresent()) {
			if (r1.getArticulos().contains(a1.get())) {
				r1.getArticulos().remove(a1.get());
				redactorService.save(r1);
				articuloRepository.deleteById(id);
				return 0;
			} else {
				return 2;
			}
		} else {
			return 1;
		}

	}
}
