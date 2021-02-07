package com.monument.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;



@Entity
@Table(name="celebrite")
public class Celebrite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="numCelebrite", unique = true)
	private int numCelebrite;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="Prenom")
	private String prenom;
	
	@Column(name="nationalite")
	private String nationalite;
	
	@Column(name="epoque")
	private String epoque;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name= "associe_a",
	joinColumns= @JoinColumn(name="CELEBRITE_ID", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="MONUMENT_ID", referencedColumnName="id"))	
	private  Set<Monument> monument;

	public Celebrite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Celebrite(long id, int numCelebrite, String nom, String prenom, String nationalite, String epoque,
			Set<Monument> monument) {
		super();
		this.id = id;
		this.numCelebrite = numCelebrite;
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
		this.epoque = epoque;
		this.monument = monument;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumCelebrite() {
		return numCelebrite;
	}

	public void setNumCelebrite(int numCelebrite) {
		this.numCelebrite = numCelebrite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getEpoque() {
		return epoque;
	}

	public void setEpoque(String epoque) {
		this.epoque = epoque;
	}

	public Set<Monument> getMonument() {
		return monument;
	}

	public void setMonument(Set<Monument> monument) {
		this.monument = monument;
	}

	@Override
	public String toString() {
		return "Celebrite [id=" + id + ", numCelebrite=" + numCelebrite + ", nom=" + nom + ", prenom=" + prenom
				+ ", nationalite=" + nationalite + ", epoque=" + epoque + ", monument=" + monument + "]";
	}

	
	
	
}
