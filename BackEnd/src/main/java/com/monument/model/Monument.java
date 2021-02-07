package com.monument.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="monument")
public class Monument implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="codeM",unique= true, length=50)
	private String codeM;
	
	@Column(name="nomM")
	private String nomM;
	
	@Column(name="proprietaire")
	private String proprietaire;
	
	@Column(name="typeMonument")
	private String typeMonument;
	
	@Column (name="longitude")
	private float longitude;
	
	@Column(name="latitude")
	private float latitude;
	
	@ManyToOne(targetEntity = Lieux.class)
	@JoinColumn(name="LIEUX_ID")
	private Lieux lieux;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(joinColumns={@JoinColumn(referencedColumnName="id")}
    , inverseJoinColumns={@JoinColumn(referencedColumnName="id")})
	private Set<Celebrite> celebrite;

	public Monument() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Monument(long id, String codeM, String nomM, String proprietaire, String typeMonument, float longitude,
			float latitude, Lieux lieux, Set<Celebrite> celebrite) {
		super();
		this.id = id;
		this.codeM = codeM;
		this.nomM = nomM;
		this.proprietaire = proprietaire;
		this.typeMonument = typeMonument;
		this.longitude = longitude;
		this.latitude = latitude;
		this.lieux = lieux;
		this.celebrite = celebrite;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodeM() {
		return codeM;
	}

	public void setCodeM(String codeM) {
		this.codeM = codeM;
	}

	public String getNomM() {
		return nomM;
	}

	public void setNomM(String nomM) {
		this.nomM = nomM;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getTypeMonument() {
		return typeMonument;
	}

	public void setTypeMonument(String typeMonument) {
		this.typeMonument = typeMonument;
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

	public Lieux getLieux() {
		return lieux;
	}

	public void setLieux(Lieux lieux) {
		this.lieux = lieux;
	}

	public Set<Celebrite> getCelebrite() {
		return celebrite;
	}

	public void setCelebrite(Set<Celebrite> celebrite) {
		this.celebrite = celebrite;
	}

	@Override
	public String toString() {
		return "Monument [id=" + id + ", codeM=" + codeM + ", nomM=" + nomM + ", proprietaire=" + proprietaire
				+ ", typeMonument=" + typeMonument + ", longitude=" + longitude + ", latitude=" + latitude + ", lieux="
				+ lieux + ", celebrite=" + celebrite + "]";
	}
	
	

}
