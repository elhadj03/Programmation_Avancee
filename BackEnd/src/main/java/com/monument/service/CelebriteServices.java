package com.monument.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monument.model.Celebrite;
import com.monument.repository.CelebriteRepository;

@Service
public class CelebriteServices {
	@Autowired
	CelebriteRepository celebriteRepository;
	
	public Celebrite saveCelebrite(Celebrite celebrite) {
		return celebriteRepository.save(celebrite);
	}
	
	public List<Celebrite> getCelebriteInfo(){
		return celebriteRepository.findAll();
	}
	
	public List<Celebrite> getCelebrite(String nom){
		return celebriteRepository.findByNomContaining(nom);
	}
	
	public Optional<Celebrite>getCelebriteById(long id){
		return celebriteRepository.findById(id);
	}
	
	public boolean checkExistCelebrite(long id) {
		if(celebriteRepository.existsById((long) id)) {
			return true;
		}
		return false;
	}
	
	public Celebrite updateCelebrite(Celebrite celebrite) {
		return celebriteRepository.save(celebrite);
	}
	
	public void deleteCelebriteById(long id) {
		 celebriteRepository.deleteById(id);
	}
}
