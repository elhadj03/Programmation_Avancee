package com.monument.model;

import java.util.ArrayList;
import java.util.List;

public class Message {
	private String message = "";
	private List<Lieux> lieux = new ArrayList<Lieux>();
	private List<Departement> departement = new ArrayList<Departement>();
	private List<Celebrite> celebrite = new ArrayList<Celebrite>();
	private List<Monument> monument = new ArrayList<Monument>();
	private String error = "";
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Message(String message, List<Lieux> lieux, List<Departement> departement, List<Celebrite> celebrite,
			List<Monument> monument, String error) {
		super();
		this.message = message;
		this.lieux = lieux;
		this.departement = departement;
		this.celebrite = celebrite;
		this.monument = monument;
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Lieux> getLieux() {
		return lieux;
	}
	public void setLieux(List<Lieux> lieux) {
		this.lieux = lieux;
	}
	public List<Departement> getDepartement() {
		return departement;
	}
	public void setDepartement(List<Departement> departement) {
		this.departement = departement;
	}
	public List<Celebrite> getCelebrite() {
		return celebrite;
	}
	public void setCelebrite(List<Celebrite> celebrite) {
		this.celebrite = celebrite;
	}
	public List<Monument> getMonument() {
		return monument;
	}
	public void setMonument(List<Monument> monument) {
		this.monument = monument;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
}
