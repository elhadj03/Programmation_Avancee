package com.monument.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="departement")
public class Departement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="dep")
	private String dep;
	
	@Column(name="nomDep")
	private String nomDep;
	
	@Column(name="numReg")
	private String numReg;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="LIEUX_ID")
	private Lieux lieux;

	public Departement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Departement(long id, String dep, String nomDep, String numReg, Lieux lieux) {
		super();
		this.id = id;
		this.dep = dep;
		this.nomDep = nomDep;
		this.numReg = numReg;
		this.lieux = lieux;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getNomDep() {
		return nomDep;
	}

	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
	}

	public String getNumReg() {
		return numReg;
	}

	public void setNumReg(String numReg) {
		this.numReg = numReg;
	}

	public Lieux getLieux() {
		return lieux;
	}

	public void setLieux(Lieux lieux) {
		this.lieux = lieux;
	}

	@Override
	public String toString() {
		return "Departement [id=" + id + ", dep=" + dep + ", nomDep=" + nomDep + ", numReg=" + numReg + ", lieux="
				+ lieux + "]";
	}
	
	

}
