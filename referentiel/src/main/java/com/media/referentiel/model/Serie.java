package com.media.referentiel.model;

import java.util.ArrayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "Serie")
@DiscriminatorValue("S")
public class Serie extends Media{
	
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
	private ArrayList<ArrayList<String>> saisons = new ArrayList<ArrayList<String>>();
	private long duree;
	private int annee_sortie;
	
	public Serie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Serie(String titre, String genre, String categorie, String description, String paysAuthorise, boolean visibility) {
		super(titre, genre, categorie,description, paysAuthorise,  visibility);
		// TODO Auto-generated constructor stub
	}

	public Serie(String titre, String genre, String categorie, String description, String paysAuthorise, boolean visibility, ArrayList<ArrayList<String>> saisons, long duree, int annee_sortie) {
		super(titre, genre, categorie, description, paysAuthorise, visibility);
		this.saisons = saisons;
		this.duree = duree;
		this.annee_sortie = annee_sortie;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<ArrayList<String>> getSaisons() {
		return saisons;
	}

	public void setSaisons(ArrayList<ArrayList<String>> saisons) {
		this.saisons = saisons;
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
