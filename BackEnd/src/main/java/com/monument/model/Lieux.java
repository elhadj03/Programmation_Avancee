package com.monument.model;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="Lieux")
public class Lieux implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@Column(name="codeInsee",length = 5)
	private String codeInsee;
	
	@Column(name="nomCom")
	private String nomCom;
	
	@Column(name="longitude")
	private float longitude;
	
	@Column(name="latitude")
	private float latitude;
	
	@ManyToOne
	@JoinColumn(name="DEPARTEMENT_ID")
	private Departement departement;

	public Lieux() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lieux(long id, String codeInsee, String nomCom, float longitude, float latitude, Departement departement) {
		super();
		this.id = id;
		this.codeInsee = codeInsee;
		this.nomCom = nomCom;
		this.longitude = longitude;
		this.latitude = latitude;
		this.departement = departement;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodeInsee() {
		return codeInsee;
	}

	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
	}

	public String getNomCom() {
		return nomCom;
	}

	public void setNomCom(String nomCom) {
		this.nomCom = nomCom;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	@Override
	public String toString() {
		return "Lieux [id=" + id + ", codeInsee=" + codeInsee + ", nomCom=" + nomCom + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", departement=" + departement + "]";
	}
	
	
	
	
	
	
}
