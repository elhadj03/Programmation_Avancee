 package com.monument.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.monument.model.Message;
import com.monument.model.Monument;
import com.monument.service.MonumentServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/monument")
public class MonumentController {
	@Autowired
	MonumentServices monumentServices;
	
	//Afficher tous les Monument et Recherche par Nom Monument
	
		 @GetMapping("/retrieveinfos")
		public ResponseEntity<List<Monument>> getAllMonument(@RequestParam(required=false) String nomM){
			try {
				List<Monument> monument = new ArrayList<Monument>();
				if (nomM == null)
					monumentServices.getMonumentInfo().forEach(monument::add);
				else 
					monumentServices.getMonument(nomM).forEach(monument::add);
				if (monument.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(monument, HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		//Obtenir un Monument par id
				@GetMapping("/{id}")
				public ResponseEntity<Message> getMonumentById(@PathVariable long id){
					try {
						Optional<Monument> optMonument = monumentServices.getMonumentById(id);
						if (optMonument.isPresent()) {
							return new ResponseEntity<Message> (new Message ("Successfully! Retrieve a Monument by id = " +id,
									 null, null, null, Arrays.asList(optMonument.get()),""), HttpStatus.OK);
						}else{
							return new ResponseEntity<Message> (new Message ("Failure -> NOT Found a Monument by id = " + id,
									null, null, null, null, ""), HttpStatus.NOT_FOUND);
						}
						
					}catch (Exception e){
						return new ResponseEntity<Message>(new Message("Failure",
								null, null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
			
				//Mettre à jour un Monument
				@PutMapping("/update/{id}")
				public ResponseEntity<Message> updateMonumentById(@PathVariable("id") long id, @RequestBody Monument _monument){
					
					try {
						if(monumentServices.checkExistMonument(id)) {
							Monument monument = monumentServices.getMonumentById(id).get();
							
							//Mettre les nouvelles valeurs
							monument.setCodeM(_monument.getCodeM());
							monument.setNomM(_monument.getNomM());
							monument.setProprietaire(_monument.getProprietaire());
							monument.setTypeMonument(_monument.getTypeMonument());
							monument.setLongitude(_monument.getLongitude());
							monument.setLatitude(_monument.getLatitude());
							
							//Enregistrer les changements dans la base de données
							monumentServices.updateMonument(monument);
							
							return new ResponseEntity<Message> (new Message ("Successfully! Updated a Message " + "with id = " + id,
									 null, null, null, Arrays.asList(monument),""), HttpStatus.OK);
						}else {
							return new ResponseEntity<Message>(new Message("Failer! Can NOT Found a Monument "+ "with id = " + id,
								null, null, null, null, ""), HttpStatus.NOT_FOUND);
						}
					}catch(Exception e) {
						return new ResponseEntity<Message>(new Message ("Failure", null,
								null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
					}

				}
				
				//Supprimer un Monument
				@DeleteMapping("/delete/{id}")
				public ResponseEntity<Message> deleteMonumentbyId(@PathVariable long id){
					try {
						if(monumentServices.checkExistMonument(id)) {
							monumentServices.deleteMonumentById(id);
							return new ResponseEntity<Message> (new Message ("Successfully! monument deleted" + "with id = " + id,
									null, null, null, null, ""), HttpStatus.OK);
						}else {
							return new ResponseEntity<Message>(new Message ("Failer! Can NOT Found a Monument" + "with id = " + id,
									null, null, null, null, ""), HttpStatus.NOT_FOUND);
						}
					}catch(Exception e) {
						return new ResponseEntity<Message>(new Message ("Failure!", 
								null,null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				
			
			//Ajouter un Monument
			@PostMapping("/add")
			public ResponseEntity<Message> addNewMonument(@RequestBody(required = false)  Monument monument){
				try {
					Monument returnedMonument = monumentServices.saveMonument(monument);
					
					return new ResponseEntity<Message>(new Message("Upload Succesfully!",
							 null, null, null,Arrays.asList(returnedMonument), ""), HttpStatus.OK);
				}catch (Exception e){
					return new ResponseEntity<Message>(new Message ("Fail to post a new Lieux!",
							null, null, null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
			}
		
}
