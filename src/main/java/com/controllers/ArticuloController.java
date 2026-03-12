package com.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Articulo;
import com.services.ArticuloService;



@RestController
@RequestMapping("/articulo")
public class ArticuloController {
	@Autowired

	private ArticuloService articuloService;
	
	@GetMapping
	public ResponseEntity<List<Articulo>> findAll(){
		return ResponseEntity.ok(articuloService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Articulo> findOne(@PathVariable int id){
		Optional<Articulo> a1 = articuloService.findOne(id);
		
		if(a1.isPresent()) {
			return ResponseEntity.ok(a1.get());
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	@PostMapping
	public ResponseEntity<String> save(@RequestBody Articulo a){
		articuloService.save(a);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
