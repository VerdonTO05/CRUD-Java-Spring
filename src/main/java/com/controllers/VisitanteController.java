package com.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Visitante;
import com.services.VisitanteService;
@RestController
@RequestMapping("/visitante")
public class VisitanteController {
	@Autowired

	private VisitanteService visitanteService;

	@GetMapping
	public ResponseEntity<List<Visitante>> findAll() {
		return ResponseEntity.ok(visitanteService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Visitante> findOne(@PathVariable int id) {
		Optional<Visitante> a1 = visitanteService.findOne(id);

		if (a1.isPresent()) {
			return ResponseEntity.ok(a1.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@PostMapping
	public ResponseEntity<String> save(@RequestBody Visitante a) {
		visitanteService.save(a);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody Visitante a, @PathVariable int id) {
		Optional<Visitante> a1 = visitanteService.findOne(id);

		if (a1.isPresent()) {
			a.setId(id);
			visitanteService.save(a);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable int id){
		Optional<Visitante> a = visitanteService.findOne(id);
		
		if(a.isPresent()) {
			visitanteService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
