package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.PerfilSocial;
import com.repository.PerfilSocialRepository;

@Service
public class PerfilSocialService {
	@Autowired
	
	private PerfilSocialRepository perfilSocialRepository;
	
	public List<PerfilSocial> findAll(){
		
		return perfilSocialRepository.findAll();
	}
	
	public Optional<PerfilSocial> findOne(int id){
		return perfilSocialRepository.findById(id);
	}
	
	public PerfilSocial save(PerfilSocial p) {
		return perfilSocialRepository.save(p);
	}
	
	public PerfilSocial update(int id,PerfilSocial p) {
		if(findOne(id).isPresent()) {
			PerfilSocial pf = findOne(id).get();
			pf.setUrl(p.getUrl());
			return perfilSocialRepository.save(pf);
		}else {
			return null;
		}
	}
	
	
	public Boolean delete(int id) {
		if(findOne(id).isPresent()) {
			perfilSocialRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
}
