package com.catalogue.poster.controler;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.catalogue.poster.model.Poster;
import com.catalogue.poster.service.PosterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PosterControler {

	@Autowired
	private PosterService posterService;
	RestTemplate restTemplate = new RestTemplate();	
	String urlUser = "http://usernetflixapi_server_1:5000/";
	String urlMedia = "http://media-webservice-app:8080/";
//	String urlUser = "http://localhost:5000/";
//	String urlMedia = "http://localhost:8080/";
	ObjectMapper mapper = new ObjectMapper();
	
	@PostMapping("/posters")
	public ResponseEntity<List<Poster>> getAllPoster(@RequestBody Integer user_id) throws JsonProcessingException{
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>(headers);
		Object obj = restTemplate.exchange(urlUser+"users/"+user_id, HttpMethod.GET,request,  Object.class).getBody();
		
		if( Objects.isNull(obj)) {
			System.out.println("Api User return null");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		System.out.println(obj);
		String jsonStr = mapper.writeValueAsString(obj);
		JSONObject json = new JSONObject(jsonStr);
		if (json.getString("status").equals("Actif")) {
			return ResponseEntity.ok(posterService.getAllPoster());
		}
		System.out.println("User is not Actif");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@GetMapping("/posters/{poster_id}/{user_id}")
	public ResponseEntity<Optional<Poster>> getPoster(@PathVariable String poster_id, @PathVariable Integer user_id) throws JsonProcessingException{
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>(headers);
		Object obj = restTemplate.exchange(urlUser+"users/"+user_id, HttpMethod.GET,request,  Object.class).getBody();
		
		if( Objects.isNull(obj)) {
			System.out.println("Api User return null");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		System.out.println(obj);
		String jsonStr = mapper.writeValueAsString(obj);
		JSONObject json = new JSONObject(jsonStr);
		if (json.getString("status").equals("Actif")) {
			return ResponseEntity.ok(posterService.getPoster(poster_id));
		}
		System.out.println("User is not Actif");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	@PostMapping("/posters/genre")
	public ResponseEntity<List<Poster>> getAllPosterByGenre(@RequestParam(name = "user_id") String user_id, @RequestBody List<Object> medias) throws JsonProcessingException{
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>(headers);
		Object obj = restTemplate.exchange(urlUser+"users/"+user_id, HttpMethod.GET,request,  Object.class).getBody();
		
		if( Objects.isNull(obj)) {
			System.out.println("Api User return null");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		System.out.println(obj);
		String jsonStr = mapper.writeValueAsString(obj);
		JSONObject json = new JSONObject(jsonStr);
		if (json.getString("status").equals("Actif")) {
			String MediaStr = mapper.writeValueAsString(medias);
			JSONArray jsonMedia = new JSONArray(MediaStr);
			return ResponseEntity.ok(posterService.getAllPosterByGenre(jsonMedia));
		}
		System.out.println("User is not Actif");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@PostMapping("/posters/categorie")
	public ResponseEntity<List<Poster>> getAllPosterByCategorie(@RequestParam(name = "user_id") String user_id, @RequestBody List<Object> medias) throws JsonProcessingException{
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>(headers);
		Object obj = restTemplate.exchange(urlUser+"users/"+user_id, HttpMethod.GET,request,  Object.class).getBody();
		
		if( Objects.isNull(obj)) {
			System.out.println("Api User return null");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		System.out.println(obj);
		String jsonStr = mapper.writeValueAsString(obj);
		JSONObject json = new JSONObject(jsonStr);
		if (json.getString("status").equals("Actif")) {
			String MediaStr = mapper.writeValueAsString(medias);
			JSONArray jsonMedia = new JSONArray(MediaStr);
			return ResponseEntity.ok(posterService.getAllPosterByCategorie(jsonMedia));
		}
		System.out.println("User is not Actif");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@PostMapping("/posters/current")
	public ResponseEntity<String> getCurrentPosterUrl(@RequestParam(name = "user_id") String user_id, @RequestParam(name = "media_id") Long media_id) throws JsonProcessingException{
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>(headers);
		Object obj = restTemplate.exchange(urlUser+"users/"+user_id, HttpMethod.GET,request,  Object.class).getBody();
		
		if( Objects.isNull(obj)) {
			System.out.println("Api User return null");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		System.out.println(obj);
		String jsonStr = mapper.writeValueAsString(obj);
		JSONObject json = new JSONObject(jsonStr);
		if (json.getString("status").equals("Actif")) {
			return ResponseEntity.ok(posterService.getCurrentPoster(media_id));
		}
		System.out.println("User is not Actif");
		
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@PostMapping("/posters/add")
	public ResponseEntity<Poster> createPoster(@RequestParam(name = "user_id") String user_id, @RequestBody Poster poster) throws JsonProcessingException{
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>(headers);
		Object objUser = restTemplate.exchange(urlUser+"users/"+user_id, HttpMethod.GET,request,  Object.class).getBody();
		Object objMedia = restTemplate.exchange(urlMedia+"medias/"+poster.getmediaId()+"/"+user_id, HttpMethod.GET,request,  Object.class).getBody();

		if( Objects.isNull(objUser) || Objects.isNull(objMedia)) {
			System.out.println("Api User return null or api media return null");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		System.out.println(objUser);
		String jsonStr = mapper.writeValueAsString(objUser);
		JSONObject json = new JSONObject(jsonStr);
		if (json.getString("role").equals("Presta")) {
			return ResponseEntity.ok(posterService.createPoster(poster));
			
		}
		System.out.println("User is not Presta");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@PutMapping("/posters/edit")
	public ResponseEntity<Poster> editPoster(@RequestParam(name = "user_id") Integer user_id, @RequestBody Poster poster) throws JsonProcessingException{
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>(headers);
		Object obj = restTemplate.exchange(urlUser+"users/"+user_id, HttpMethod.GET,request,  Object.class).getBody();
		
		if( Objects.isNull(obj)) {  
			System.out.println("Api User return null");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		System.out.println(obj);
		String jsonStr = mapper.writeValueAsString(obj);
		JSONObject json = new JSONObject(jsonStr);
		if (json.getString("role").equals("Presta")) {
			return ResponseEntity.ok(posterService.createPoster(poster));
			
		}
		System.out.println("User is not Actif");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@DeleteMapping("/posters/delete/{media_id}")
	public ResponseEntity<String> deletePoster(@PathVariable Long media_id){
		String res = posterService.deletePosterByMediaId(media_id);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
