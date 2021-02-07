package com.monument.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.monument.model.Lieux;
import com.monument.model.Message;

import com.monument.service.LieuxServices;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/lieux")
public class LieuxController {
	@Autowired
	LieuxServices lieuxServices;
	

	// Recuperer tous les lieux
	/*@GetMapping("/retrieveinfos")
	public ResponseEntity<Message> retrieveLieuxInfo(){
		try {
			List<Lieux> lieuxInfo = lieuxServices.getLieuxInfo();
			
			return new ResponseEntity<Message>(new Message ("Get Lieux Infos",
					lieuxInfo, ""), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Message>(new Message ("Fail!", null,
					e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	*/
	//Recherche par nom commune
	
	 @GetMapping("/retrieveinfos")
	public ResponseEntity<List<Lieux>> getAllLieu(@RequestParam(required=false) String nomCom){
		try {
			List<Lieux> lieux = new ArrayList<Lieux>();
			if (nomCom == null)
				lieuxServices.getLieuxInfo().forEach(lieux::add);
			else 
				lieuxServices.getLieux(nomCom).forEach(lieux::add);
			if (lieux.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(lieux, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Obtenir un lieux par id
		@GetMapping("/{id}")
		public ResponseEntity<Message> getLieuxById(@PathVariable long id){
			try {
				Optional<Lieux> optLieux = lieuxServices.getLieuxById(id);
				if (optLieux.isPresent()) {
					return new ResponseEntity<Message> (new Message ("Successfully! Retrieve a Lieux by id = " +id,
							Arrays.asList(optLieux.get()), null, null, null, ""), HttpStatus.OK);
				}else{
					return new ResponseEntity<Message> (new Message ("Failure -> NOT Found a Lieux by id = " + id,
							null, null, null, null, ""), HttpStatus.NOT_FOUND);
				}
				
			}catch (Exception e){
				return new ResponseEntity<Message>(new Message("Failure",
						null, null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
		//Mettre à jour un lieux
		@PutMapping("/update/{id}")
		public ResponseEntity<Message> updateLieuxById(@PathVariable("id") long id, @RequestBody Lieux _lieux){
			
			try {
				if(lieuxServices.checkExistLieux(id)) {
					Lieux lieux = lieuxServices.getLieuxById(id).get();
					
					//Mettre les nouvelles valeurs
					lieux.setCodeInsee(_lieux.getCodeInsee());
					lieux.setNomCom(_lieux.getNomCom());
					lieux.setLongitude(_lieux.getLongitude());
					lieux.setLatitude(_lieux.getLatitude());
					
					//Enregistrer les changements dans la base de données
					lieuxServices.updateLieux(lieux);
					
					return new ResponseEntity<Message> (new Message ("Successfully! Updated a Lieux " + "with id = " + id,
							Arrays.asList(lieux), null, null, null, ""), HttpStatus.OK);
				}else {
					return new ResponseEntity<Message>(new Message("Failer! Can NOT Found a Lieux "+ "with id = " + id,
						null, null, null, null, ""), HttpStatus.NOT_FOUND);
				}
			}catch(Exception e) {
				return new ResponseEntity<Message>(new Message ("Failure", null,
						null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			

		}
		
		//Supprimer un lieu
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Message> deleteLieuxbyId(@PathVariable long id){
			try {
				if(lieuxServices.checkExistLieux(id)) {
					lieuxServices.deleteLieuxById(id);
					return new ResponseEntity<Message> (new Message ("Successfully! lieux deleted" + "with id = " + id,
							null, null, null, null, ""), HttpStatus.OK);
				}else {
					return new ResponseEntity<Message>(new Message ("Failer! Can NOT Found a Lieux" + "with id = " + id,
							null, null, null, null, ""), HttpStatus.NOT_FOUND);
				}
			}catch(Exception e) {
				return new ResponseEntity<Message>(new Message ("Failure!", 
						null,null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	
	//Ajouter un lieux
	@PostMapping("/add")
	public ResponseEntity<Message> addNewLieux(@RequestBody(required = false)  Lieux lieux){
		try {
			Lieux returnedLieux = lieuxServices.saveLieux(lieux);
			
			return new ResponseEntity<Message>(new Message("Upload Succesfully!",
					Arrays.asList(returnedLieux), null, null, null, ""), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<Message>(new Message ("Fail to post a new Lieux!",
					null, null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
}
