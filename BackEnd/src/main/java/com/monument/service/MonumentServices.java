package com.monument.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monument.model.Monument;
import com.monument.repository.*;

@Service
public class MonumentServices {
	@Autowired
	MonumentRepository MonumentRepository;
	
	public Monument saveMonument(Monument Monument) {
		return MonumentRepository.save(Monument);
	}
	
	public List<Monument> getMonumentInfo(){
		return MonumentRepository.findAll();
	}
	
	public List<Monument> getMonument(String nomM){
		return MonumentRepository.findByNomMContaining(nomM);
	}
	
	public Optional<Monument>getMonumentById(long id){
		return MonumentRepository.findById(id);
	}
	
	public boolean checkExistMonument(long id) {
		if(MonumentRepository.existsById((long) id)) {
			return true;
		}
		return false;
	}
	
	public Monument updateMonument(Monument Monument) {
		return MonumentRepository.save(Monument);
	}
	
	public void deleteMonumentById(long id) {
		 MonumentRepository.deleteById(id);
	}

}
