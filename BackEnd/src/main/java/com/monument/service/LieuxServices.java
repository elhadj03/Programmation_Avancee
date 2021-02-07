package com.monument.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monument.model.Lieux;
import com.monument.repository.LieuxRepository;
import java.util.*;

@Service
public class LieuxServices {
	@Autowired
	LieuxRepository lieuxRepository;
	
	public Lieux saveLieux(Lieux lieux) {
		return lieuxRepository.save(lieux);
	}
	
	public List<Lieux> getLieuxInfo(){
		return lieuxRepository.findAll();
	}
	
	public List<Lieux> getLieux(String nomCom){
		return lieuxRepository.findByNomComContaining(nomCom);
	}
	
	public Optional<Lieux>getLieuxById(long id){
		return lieuxRepository.findById(id);
	}
	
	public boolean checkExistLieux(long id) {
		if(lieuxRepository.existsById((long) id)) {
			return true;
		}
		return false;
	}
	
	public Lieux updateLieux(Lieux lieux) {
		return lieuxRepository.save(lieux);
	}
	
	public void deleteLieuxById(long id) {
		lieuxRepository.deleteById(id);
	}
}
