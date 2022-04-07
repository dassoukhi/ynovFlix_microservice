package com.media.referentiel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;

import com.media.referentiel.model.Film;
import com.media.referentiel.model.Media;
import com.media.referentiel.repository.MediaRepository;


@Service
public class MediaService {
	
	@Autowired
	private MediaRepository mediaRepo;
	
	public List<Media> getAllMedia(String paysAuthorise){
		List<Media> li = mediaRepo.findAllByPaysAuthorise(paysAuthorise);
		CollectionUtils.filter(li, o -> ((Media) o).isVisibility());
		return li;
	}
	
	public List<Media> getAllByCategorie(String categorie, String paysAuthorise){
		List<Media> li = mediaRepo.findAllByCategorie(categorie);
		CollectionUtils.filter(li, o -> ((Media) o).isVisibility() && ((Media) o).getPaysAuthorise().equals(paysAuthorise));
		return li;
	}
	
	public List<Media> getAllByGenre(String genre, String paysAuthorise){
		List<Media> li = mediaRepo.findAllByGenre(genre);
		CollectionUtils.filter(li, o -> ((Media) o).isVisibility() && ((Media) o).getPaysAuthorise().equals(paysAuthorise));
		return li;
	}
	
	public Optional<Media> getMedia(Long id, String paysAuthorise) {
		Optional<Media> m = mediaRepo.findById(id);
		if (m.get().isVisibility() && m.get().getPaysAuthorise().equals(paysAuthorise)) {
			return m;
		}
		return null;
	}
	
	public Media createMedia(Media media) {
		
		return mediaRepo.save(media);
	}
	public Media updateMedia(Media media) {
		
		return mediaRepo.save(media);
	}
	
	public Long deleteMedia(Long id) {
		Media m = mediaRepo.findById(id).get();
		m.setVisibility(false);
		mediaRepo.save(m);
		return m.getId();
	}
}
