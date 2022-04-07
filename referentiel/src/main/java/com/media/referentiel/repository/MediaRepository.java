package com.media.referentiel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.media.referentiel.model.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long>{

	List<Media> findAllByCategorie(String categorie);
	List<Media> findAllByGenre(String genre);
	List<Media> findAllByPaysAuthorise(String paysAuthorise);
	
}

