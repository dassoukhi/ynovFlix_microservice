package com.catalogue.poster.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;

import com.catalogue.poster.model.Poster;
import com.catalogue.poster.repository.PosterRepository;

import io.swagger.v3.core.util.Json;

@Service
public class PosterService {

	@Autowired
	private PosterRepository posterRepo;
	
	void updateCurrentPoster(Poster poster) {
		
		if (LocalDateTime.now().getHour() < 12) {
			poster.setCurrentPoster(poster.getUrlMatin());
		}else {
			poster.setCurrentPoster(poster.getUrlSoir());
		}
	}
	public List<Poster> getAllPoster(){
		List<Poster> res = posterRepo.findAll();
		CollectionUtils.filter(res, o -> ((Poster) o).isVisibility());
		
		for (Poster poster : res) {
			updateCurrentPoster(poster);
		}
		return res;
		
	}
	
	public Optional<Poster> getPoster(String id) {
		Optional<Poster> poster = posterRepo.findById(id);
		try {
			updateCurrentPoster(poster.get());
		 } catch (Exception e) {
			 System.out.println(e);
		 }
		return poster;
	}
	
	public Poster createPoster(Poster poster) {
		return posterRepo.save(poster);
	}
	
	public String deletePosterByMediaId(Long id) {
		Poster p = posterRepo.findByMediaId(id);
		p.setVisibility(false);
		posterRepo.save(p);
		return p.getId();
	}
	
	public String getCurrentPoster(Long id) {
		Poster p = posterRepo.findByMediaId(id);
		if (LocalDateTime.now().getHour() < 12) {
			return p.getUrlMatin();
		}
		return p.getUrlSoir();
		
	}
	
	public List<Poster> getAllPosterByGenre(JSONArray mediaList) {
		
		List<Poster> allPoster = getAllPoster();
		List<Poster> posterByGenre = new ArrayList<Poster>();
		for (int i = 0; i < mediaList.length(); i++) {
			
			Long media_id  = mediaList.getJSONObject(i).getLong("id");
			System.out.println("mediaId : "+media_id);
			Poster matchingPoster = allPoster.stream().
				    filter(p -> p.getmediaId().equals(media_id)).
				    findFirst().get();
			System.out.println("posterFound: "+ matchingPoster);
			if (matchingPoster != null) {
				posterByGenre.add(matchingPoster);
			}
		
		}
		
		return posterByGenre;		
		
	}
	
public List<Poster> getAllPosterByCategorie(JSONArray mediaList) {
		
		List<Poster> allPoster = getAllPoster();
		List<Poster> posterByCategorie = new ArrayList<Poster>();
		for (int i = 0; i < mediaList.length(); i++) {
			
			Long media_id  = mediaList.getJSONObject(i).getLong("id");
			System.out.println("mediaId : "+media_id);
			Poster matchingPoster = allPoster.stream().
				    filter(p -> p.getmediaId().equals(media_id)).
				    findFirst().get();
			System.out.println("posterFound: "+ matchingPoster);
			if (matchingPoster != null) {
				posterByCategorie.add(matchingPoster);
			}
		
		}
		
		return posterByCategorie;		
		
	}
}
