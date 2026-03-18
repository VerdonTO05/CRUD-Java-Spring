package com.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.Actor;
import com.repository.ActorRepository;

@Service
public class ActorService implements UserDetailsService {
	@Autowired
	private ActorRepository actorRepository;

	public List<Actor> findAll() {
		return actorRepository.findAll();
	}

	public Optional<Actor> findOne(int id) {
		return actorRepository.findById(id);
	}

	public Optional<Actor> findByUsername(String nombre) {
		return actorRepository.findByUsername(nombre);
	}

	public Actor save(Actor a) {
		return actorRepository.save(a);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Actor> actorO = this.findByUsername(username);
		if (actorO.isPresent()) {
			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(actorO.get().getRol().toString()));
			User user = new User(actorO.get().getUsername(), actorO.get().getPassword(), authorities);
			return user;
		} else {
			throw new UsernameNotFoundException("Username no encontrado");
		}
	}

	public Actor update(Actor a, int id) {
		if (findOne(id).isPresent()) {
			Actor actorbd = findOne(id).get();
			actorbd.setNombre(a.getNombre());
			actorbd.setPassword(a.getPassword());
			actorbd.setApellido1(a.getApellido1());
			actorbd.setApellido1(a.getApellido2());
			actorbd.setCorreo(a.getCorreo());
			actorbd.setTelefono(a.getTelefono());

			return actorRepository.save(actorbd);
		} else {
			return null;
		}

	}

	public Boolean delete(int id) {
		if (findOne(id).isPresent()) {
			actorRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
