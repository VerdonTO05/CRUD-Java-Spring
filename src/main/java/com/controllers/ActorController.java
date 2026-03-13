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

import com.entity.Actor;
import com.services.ActorService;
@RestController
@RequestMapping("/actor")
public class ActorController {
	@Autowired

	private ActorService actorService;

	@GetMapping
	public ResponseEntity<List<Actor>> findAll() {
		return ResponseEntity.ok(actorService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Actor> findOne(@PathVariable int id) {
		Optional<Actor> a1 = actorService.findOne(id);

		if (a1.isPresent()) {
			return ResponseEntity.ok(a1.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@PostMapping
	public ResponseEntity<String> save(@RequestBody Actor a) {
		actorService.save(a);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody Actor a, @PathVariable int id) {
		Optional<Actor> a1 = actorService.findOne(id);

		if (a1.isPresent()) {
			a.setId(id);
			actorService.save(a);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
