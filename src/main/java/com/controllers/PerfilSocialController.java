package com.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.PerfilSocial;
import com.services.PerfilSocialService;
@RestController
@RequestMapping("/perfil")
public class PerfilSocialController {
	@Autowired

	private PerfilSocialService perfilSocialService;

	@GetMapping
	public ResponseEntity<List<PerfilSocial>> findAll() {
		return ResponseEntity.ok(perfilSocialService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PerfilSocial> findOne(@PathVariable int id) {
		Optional<PerfilSocial> a1 = perfilSocialService.findOne(id);

		if (a1.isPresent()) {
			return ResponseEntity.ok(a1.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@PostMapping
	public ResponseEntity<String> save(@RequestBody PerfilSocial a) {
		perfilSocialService.save(a);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody PerfilSocial a, @PathVariable int id) {
		Optional<PerfilSocial> a1 = perfilSocialService.findOne(id);

		if (a1.isPresent()) {
			a.setId(id);
			perfilSocialService.save(a);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
