package com.catalogue.poster.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.catalogue.poster.model.Poster;

@Repository
public interface PosterRepository extends MongoRepository<Poster, String>{
	
	Poster findByMediaId(Long mediaId);
}
