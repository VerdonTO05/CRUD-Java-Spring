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

import com.entity.Redactor;
import com.services.RedactorService;
@RestController
@RequestMapping("/redactor")
public class RedactorController {
	@Autowired

	private RedactorService redactorService;

	@GetMapping
	public ResponseEntity<List<Redactor>> findAll() {
		return ResponseEntity.ok(redactorService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Redactor> findOne(@PathVariable int id) {
		Optional<Redactor> a1 = redactorService.findOne(id);

		if (a1.isPresent()) {
			return ResponseEntity.ok(a1.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@PostMapping
	public ResponseEntity<String> save(@RequestBody Redactor a) {
		redactorService.save(a);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody Redactor a, @PathVariable int id) {
		Optional<Redactor> a1 = redactorService.findOne(id);

		if (a1.isPresent()) {
			a.setId(id);
			redactorService.save(a);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
