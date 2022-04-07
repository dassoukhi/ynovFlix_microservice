package com.media.referentiel.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "Film")
@DiscriminatorValue("F")
public class Film extends Media{
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
	private String contenu;
	private long duree;
	private int annee_sortie;
	
	
	public Film() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Film(String titre, String genre, String categorie, String description, String paysAuthorise, boolean visibility) {
		super(titre, genre, categorie,description, paysAuthorise, visibility);
		// TODO Auto-generated constructor stub
	}
	public Film(String titre, String genre, String categorie, String description, String paysAuthorise, boolean visibility, String contenu, long duree, int annee_sortie) {
		super(titre, genre, categorie, description,paysAuthorise, visibility);
		this.contenu = contenu;
		this.duree = duree;
		this.annee_sortie = annee_sortie;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public long getDuree() {
		return duree;
	}
	public void setDuree(long duree) {
		this.duree = duree;
	}
	public int getAnnee_sortie() {
		return annee_sortie;
	}
	public void setAnnee_sortie(int annee_sortie) {
		this.annee_sortie = annee_sortie;
	}
	public boolean isVisibility() {
		return super.isVisibility();
	}
	public void setVisibility(boolean visibility) {
		super.setVisibility(visibility);
	}

	
}
