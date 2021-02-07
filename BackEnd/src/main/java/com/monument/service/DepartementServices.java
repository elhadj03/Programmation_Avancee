package com.monument.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monument.model.Departement;
import com.monument.repository.DepartementRepository;

@Service
public class DepartementServices {
	@Autowired
	DepartementRepository departementRepository;

	
	public Departement saveDepartement(Departement departement) {
		return departementRepository.save(departement);
	}
	
	public List<Departement> getDepartementInfo(){
		return departementRepository.findAll();
	}
	
	public List<Departement> getDepartement(String nomDep){
		return departementRepository.findByNomDepContaining(nomDep);
	}
	
	public Optional<Departement>getDepartementById(long id){
		return departementRepository.findById(id);
	}
	
	public boolean checkExistdepartement(long id) {
		if(departementRepository.existsById((long) id)) {
			return true;
		}
		return false;
	}
	
	public Departement updateDepartement(Departement departement) {
		return departementRepository.save(departement);
	}
	
	public void deleteDepartementById(long id) {
		 departementRepository.deleteById(id);
	}

}
