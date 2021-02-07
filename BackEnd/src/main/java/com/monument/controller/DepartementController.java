package com.monument.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.monument.model.Departement;
import com.monument.model.Message;
import com.monument.service.DepartementServices;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping(path = "/api/departement")
public class DepartementController {
	@Autowired
	DepartementServices departementServices;
	
	//Afficher tous les Departement et Recherche par Nom Commune
	
	 @GetMapping("/retrieveinfos")
	public ResponseEntity<List<Departement>> getAllDepartement(@RequestParam(required=false) String nomDep){
		try {
			List<Departement> departement = new ArrayList<Departement>();
			if (nomDep == null)
				departementServices.getDepartementInfo().forEach(departement::add);
			else 
				departementServices.getDepartement(nomDep).forEach(departement::add);
			if (departement.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(departement, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Obtenir un departement par id
			@GetMapping("/{id}")
			public ResponseEntity<Message> getDepartementById(@PathVariable long id){
				try {
					Optional<Departement> optDepartement = departementServices.getDepartementById(id);
					if (optDepartement.isPresent()) {
						return new ResponseEntity<Message> (new Message ("Successfully! Retrieve a Departement by id = " +id,
								null, Arrays.asList(optDepartement.get()), null, null, ""), HttpStatus.OK);
					}else{
						return new ResponseEntity<Message> (new Message ("Failure -> NOT Found a Departement by id = " + id,
								null, null, null, null, ""), HttpStatus.NOT_FOUND);
					}
					
				}catch (Exception e){
					return new ResponseEntity<Message>(new Message("Failure",
							null, null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		
			//Mettre à jour un departement
			@PutMapping("/update/{id}")
			public ResponseEntity<Message> updateDepartementById(@PathVariable("id") long id, @RequestBody Departement _departement){
				
				try {
					if(departementServices.checkExistdepartement(id)) {
						Departement departement = departementServices.getDepartementById(id).get();
						
						//Mettre les nouvelles valeurs
						departement.setDep(_departement.getDep());
						departement.setNomDep(_departement.getNomDep());
						departement.setNumReg(_departement.getNumReg());
						
						//Enregistrer les changements dans la base de données
						departementServices.updateDepartement(departement);
						
						return new ResponseEntity<Message> (new Message ("Successfully! Updated a Departement " + "with id = " + id,
								null, Arrays.asList(departement), null, null, ""), HttpStatus.OK);
					}else {
						return new ResponseEntity<Message>(new Message("Failer! Can NOT Found a Departement "+ "with id = " + id,
							null, null, null, null, ""), HttpStatus.NOT_FOUND);
					}
				}catch(Exception e) {
					return new ResponseEntity<Message>(new Message ("Failure", null,
							null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
				}
				

			}
			
			//Supprimer un departement
			@DeleteMapping("/delete/{id}")
			public ResponseEntity<Message> deleteDepartementbyId(@PathVariable long id){
				try {
					if(departementServices.checkExistdepartement(id)) {
						departementServices.deleteDepartementById(id);
						return new ResponseEntity<Message> (new Message ("Successfully! departement deleted" + "with id = " + id,
								null, null, null, null, ""), HttpStatus.OK);
					}else {
						return new ResponseEntity<Message>(new Message ("Failer! Can NOT Found a Departement" + "with id = " + id,
								null, null, null, null, ""), HttpStatus.NOT_FOUND);
					}
				}catch(Exception e) {
					return new ResponseEntity<Message>(new Message ("Failure!", 
							null,null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
		
		//Ajouter un Departement
		@PostMapping("/add")
		public ResponseEntity<Message> addNewDepartement(@RequestBody(required = false)  Departement departement){
			try {
				Departement returnedDepartement = departementServices.saveDepartement(departement);
				
				return new ResponseEntity<Message>(new Message("Upload Succesfully!",
						null, Arrays.asList(returnedDepartement), null, null, ""), HttpStatus.OK);
			}catch (Exception e){
				return new ResponseEntity<Message>(new Message ("Fail to post a new Lieux!",
						null, null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		

}
