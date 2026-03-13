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

import com.entity.Comentario;
import com.services.ComentarioService;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {
	@Autowired

	private ComentarioService comentarioService;

	@GetMapping
	public ResponseEntity<List<Comentario>> findAll() {
		return ResponseEntity.ok(comentarioService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Comentario> findOne(@PathVariable int id) {
		Optional<Comentario> a1 = comentarioService.findOne(id);

		if (a1.isPresent()) {
			return ResponseEntity.ok(a1.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@PostMapping
	public ResponseEntity<String> save(@RequestBody Comentario a) {
		comentarioService.save(a);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody Comentario a, @PathVariable int id) {
		Optional<Comentario> a1 = comentarioService.findOne(id);

		if (a1.isPresent()) {
			a.setId(id);
			comentarioService.save(a);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		Optional<Comentario> a = comentarioService.findOne(id);

		if (a.isPresent()) {
			comentarioService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
