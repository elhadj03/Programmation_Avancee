package com.monument.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.monument.model.*;
import com.monument.service.CelebriteServices;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/celebrite")
public class CelebriteController {
	@Autowired
	CelebriteServices celebriteServices;
	

	//Recherche par nom celebrite
		
		 @GetMapping("/retrieveinfos")
		public ResponseEntity<List<Celebrite>> getAllCelebrite(@RequestParam(required=false) String nom){
			try {
				List<Celebrite> celebrite = new ArrayList<Celebrite>();
				if (nom == null)
					celebriteServices.getCelebriteInfo().forEach(celebrite::add);
				else 
					celebriteServices.getCelebrite(nom).forEach(celebrite::add);
				if (celebrite.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(celebrite, HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		//Obtenir un celebrite par id
			@GetMapping("/{id}")
			public ResponseEntity<Message> getCelebriteById(@PathVariable long id){
				try {
					Optional<Celebrite> optLieux = celebriteServices.getCelebriteById(id);
					if (optLieux.isPresent()) {
						return new ResponseEntity<Message> (new Message ("Successfully! Retrieve a Lieux by id = " +id,
								 null, null,Arrays.asList(optLieux.get()), null, ""), HttpStatus.OK);
					}else{
						return new ResponseEntity<Message> (new Message ("Failure -> NOT Found a Lieux by id = " + id,
								null, null, null, null, ""), HttpStatus.NOT_FOUND);
					}
					
				}catch (Exception e){
					return new ResponseEntity<Message>(new Message("Failure",
							null, null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		
			//Mettre à jour un Celebrite
			@PutMapping("/update/{id}")
			public ResponseEntity<Message> updateCelebriteById(@PathVariable("id") long id, @RequestBody Celebrite _celebrite){
				
				try {
					if(celebriteServices.checkExistCelebrite(id)) {
						Celebrite celebrite = celebriteServices.getCelebriteById(id).get();
						
						//Mettre les nouvelles valeurs
	                    celebrite.setNumCelebrite(_celebrite.getNumCelebrite());
						celebrite.setNom(_celebrite.getNom());
						celebrite.setPrenom(_celebrite.getPrenom());
						celebrite.setNationalite(_celebrite.getNationalite());
						celebrite.setEpoque(_celebrite.getEpoque());
						
						//Enregistrer les changements dans la base de données
						celebriteServices.updateCelebrite(celebrite);
						
						return new ResponseEntity<Message> (new Message ("Successfully! Updated a Lieux " + "with id = " + id,
								 null, null,Arrays.asList(celebrite), null, ""), HttpStatus.OK);
					}else {
						return new ResponseEntity<Message>(new Message("Failer! Can NOT Found a Celebrite "+ "with id = " + id,
							null, null, null, null, ""), HttpStatus.NOT_FOUND);
					}
				}catch(Exception e) {
					return new ResponseEntity<Message>(new Message ("Failure", null,
							null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
				}
				

			}
			
			//Supprimer un Celebrite
			@DeleteMapping("/delete/{id}")
			public ResponseEntity<Message> deleteCelebritebyId(@PathVariable long id){
				try {
					if(celebriteServices.checkExistCelebrite(id)) {
						celebriteServices.deleteCelebriteById(id);
						return new ResponseEntity<Message> (new Message ("Successfully! Celebrite deleted" + "with id = " + id,
								null, null, null, null, ""), HttpStatus.OK);
					}else {
						return new ResponseEntity<Message>(new Message ("Failer! Can NOT Found a Celebrite" + "with id = " + id,
								null, null, null, null, ""), HttpStatus.NOT_FOUND);
					}
				}catch(Exception e) {
					return new ResponseEntity<Message>(new Message ("Failure!", 
							null,null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
		
		//Ajouter un Celebrite
		@PostMapping("/add")
		public ResponseEntity<Message> addNewCelebrite(@RequestBody(required = false)  Celebrite celebrite){
			try {
				Celebrite returnedCelebrite = celebriteServices.saveCelebrite(celebrite);
				
				return new ResponseEntity<Message>(new Message("Upload Succesfully!",
						 null, null,Arrays.asList(returnedCelebrite),null, ""), HttpStatus.OK);
			}catch (Exception e){
				return new ResponseEntity<Message>(new Message ("Fail to post a new Lieux!",
						null, null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
}
