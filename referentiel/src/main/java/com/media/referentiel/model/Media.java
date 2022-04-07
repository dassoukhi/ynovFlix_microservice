package com.media.referentiel.model;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity(name = "Media")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="media_type")
public class Media {

	@Id
	@SequenceGenerator(
			name = "media_sequence",
			sequenceName = "media_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "media_sequence"
			)
	private Long id;
	private String titre;
	private String genre;
	private String categorie;
	@Column(length = 2048)
	private String description;
	private String paysAuthorise;
	private boolean visibility;

	
	public Media() {
		
	}
	public Media(String titre, String genre, String categorie,String description, String paysAuthorise, boolean visibility) {
		this.titre = titre;
		this.genre = genre;
		this.categorie = categorie;
		this.description = description;
		this.paysAuthorise = paysAuthorise;
		this.visibility = visibility;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPaysAuthorise() {
		return paysAuthorise;
	}
	public void setPaysAuthorise(String paysAuthorise) {
		this.paysAuthorise = paysAuthorise;
	}
	public boolean isVisibility() {
		return visibility;
	}
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
	
	
}
