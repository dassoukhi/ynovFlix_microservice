package com.catalogue.poster.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Poster")
public class Poster {

	@Id
	private String id;
	private String titre;
	private String urlMatin;
	private String urlSoir;
	private String currentPoster;
	private boolean visibility;
	@Indexed(unique=true)
	@Field(name = "mediaId")
	private Long mediaId;
	
	
	public Poster() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Poster(String titre, String urlMatin, String urlSoir, boolean visibility, Long mediaId) {
		this.titre = titre;
		this.urlMatin = urlMatin;
		this.urlSoir = urlSoir;
		this.visibility = visibility;
		this.mediaId = mediaId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getUrlMatin() {
		return urlMatin;
	}
	public void setUrlMatin(String urlMatin) {
		this.urlMatin = urlMatin;
	}
	public String getUrlSoir() {
		return urlSoir;
	}
	public void setUrlSoir(String urlSoir) {
		this.urlSoir = urlSoir;
	}
	
	public String getCurrentPoster() {
		return currentPoster;
	}
	public void setCurrentPoster(String currentPoster) {
		this.currentPoster = currentPoster;
	}
	
	public boolean isVisibility() {
		return visibility;
	}
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
	public Long getmediaId() {
		return mediaId;
	}
	public void setmediaId(Long mediaId) {
		this.mediaId = mediaId;
	}
	
	
	
}
