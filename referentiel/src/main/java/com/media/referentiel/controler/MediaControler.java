package com.media.referentiel.controler;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.media.referentiel.model.Film;
import com.media.referentiel.model.Media;
import com.media.referentiel.model.Serie;
import com.media.referentiel.service.MediaService;

@RestController
public class MediaControler {
	
	@Autowired
	private MediaService mediaService;
	RestTemplate restTemplate = new RestTemplate();	
	String urlUser = "http://usernetflixapi_server_1:5000/";
	String urlPoster = "http://poster-webservice-app:8081/";
//	String urlUser = "http://localhost:5000/";
//	String urlPoster = "http://localhost:8081/";
	ObjectMapper mapper = new ObjectMapper();
	
	@PostMapping("/medias")
	public ResponseEntity<List<Media>>getAllMedia(@RequestBody Integer user_id) throws JsonProcessingException {
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
			String cible = json.getString("pays");
			return ResponseEntity.ok(mediaService.getAllMedia(cible));
		}
		System.out.println("User is not Actif");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}
	
	@PostMapping("/medias/posters")
	public ResponseEntity<List<Object>> getAllPosterFromPosterService(@RequestBody Integer user_id) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<Integer> request = new HttpEntity<Integer>(user_id, headers);
		
		Object[] obj = restTemplate.exchange(urlPoster+"posters", HttpMethod.POST, request,  Object[].class).getBody();
		
		if( Objects.isNull(obj)) {
			System.out.println("Api User return null");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.ok(Arrays.asList(obj));

	}
	
	@PostMapping("/medias/posters/genre")
	public ResponseEntity<List<Object>> getAllPosterFromPosterServiceByGenre(@RequestParam(name = "user_id") Integer user_id, @RequestParam(name = "genre") String genre) throws JsonProcessingException {
		HttpHeaders headersUser = new HttpHeaders();
		headersUser.add("Content-Type", "application/json");
		HttpEntity<String> requestUser = new HttpEntity<String>(headersUser);
		
		Object obj = restTemplate.exchange(urlUser+"users/"+user_id, HttpMethod.GET,requestUser,  Object.class).getBody();
		
		if( Objects.isNull(obj)) {
			System.out.println("Api User return null");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		System.out.println(obj);
		String jsonStr = mapper.writeValueAsString(obj);
		JSONObject json = new JSONObject(jsonStr);
		List<Media> mediaByGenre = null;
		if (json.getString("status").equals("Actif")) {
			String cible = json.getString("pays");
			mediaByGenre = mediaService.getAllByGenre(genre, cible);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			HttpEntity<List<Media>> request = new HttpEntity<List<Media>>(mediaByGenre, headers);
			
			Object[] objList = restTemplate.exchange(urlPoster+"/posters/genre?user_id="+user_id, HttpMethod.POST, request,  Object[].class).getBody();
			
			if( Objects.isNull(objList)) {
				System.out.println("Api Poster return null");
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			return ResponseEntity.ok(Arrays.asList(objList));
			
		}
		
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}
	
	@PostMapping("/medias/posters/categorie")
	public ResponseEntity<List<Object>> getAllPosterFromPosterServiceByCategorie(@RequestParam(name = "user_id") Integer user_id, @RequestParam(name = "categorie") String categorie) throws JsonProcessingException {
		HttpHeaders headersUser = new HttpHeaders();
		headersUser.add("Content-Type", "application/json");
		HttpEntity<String> requestUser = new HttpEntity<String>(headersUser);
		
		Object obj = restTemplate.exchange(urlUser+"users/"+user_id, HttpMethod.GET,requestUser,  Object.class).getBody();
		
		if( Objects.isNull(obj)) {
			System.out.println("Api User return null");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		System.out.println(obj);
		String jsonStr = mapper.writeValueAsString(obj);
		JSONObject json = new JSONObject(jsonStr);
		List<Media> mediaByCategorie = null;
		if (json.getString("status").equals("Actif")) {
			String cible = json.getString("pays");
			mediaByCategorie = mediaService.getAllByCategorie(categorie, cible);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			HttpEntity<List<Media>> request = new HttpEntity<List<Media>>(mediaByCategorie, headers);
			
			Object[] objList = restTemplate.exchange(urlPoster+"/posters/categorie?user_id="+user_id, HttpMethod.POST, request,  Object[].class).getBody();
			
			if( Objects.isNull(objList)) {
				System.out.println("Api Poster return null");
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			return ResponseEntity.ok(Arrays.asList(objList));
			
		}
		
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}
	
	@PostMapping("/medias/categorie/{categorie}")
	public ResponseEntity<List<Media>> getAllByCategorie(@PathVariable String categorie, @RequestBody Integer user_id) throws JsonProcessingException {
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
			String cible = json.getString("pays");
			return ResponseEntity.ok(mediaService.getAllByCategorie(categorie, cible));
		}
		System.out.println("User is not Actif");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}
	
	@PostMapping("/medias/genre/{genre}")
	public ResponseEntity<List<Media>> getAllByGenre(@PathVariable String genre, @RequestBody Integer user_id) throws JsonProcessingException {
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
			String cible = json.getString("pays");
			return ResponseEntity.ok(mediaService.getAllByGenre(genre, cible));
		}
		System.out.println("User is not Actif");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}
	
	@GetMapping("/medias/{media_id}/{user_id}")
	public ResponseEntity<Optional<Media>> getMedia(@PathVariable Long media_id, @PathVariable Integer user_id) throws JsonProcessingException {
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
			return ResponseEntity.ok(mediaService.getMedia(media_id, json.getString("pays")));
		}
		System.out.println("User is not Actif");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@PostMapping("/films/add")
	public ResponseEntity<Film> addMedia(@RequestParam(name = "user_id") String user_id, @RequestBody Film film) throws JsonProcessingException {
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
		if (json.getString("role").equals("Admin")) {
			return ResponseEntity.ok((Film) mediaService.createMedia(film));
			
		}
		System.out.println("User is not Admin");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@PostMapping("/series/add")
	public ResponseEntity<Serie> addMedia(@RequestParam(name = "user_id") String user_id, @RequestBody Serie serie) throws JsonProcessingException {
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
		if (json.getString("role").equals("Admin")) {
			return ResponseEntity.ok((Serie) mediaService.createMedia(serie));
			
		}
		System.out.println("User is not Admin");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@PostMapping("/medias/currentposter")
	public ResponseEntity<String> getCurrentePoster(@RequestParam(name = "user_id") String user_id, @RequestParam(name = "media_id") Long media_id) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>( headers);
		
		String strCurrent = restTemplate.exchange(urlPoster+"posters/current?user_id="+user_id+"&media_id="+media_id, HttpMethod.POST, request,  String.class).getBody();
		
		if( Objects.isNull(strCurrent)) {
			System.out.println("Api User return null");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		System.out.println(strCurrent);
		return ResponseEntity.ok(strCurrent);
	}
	
	@PutMapping("/medias/update/descrtiption")
	public ResponseEntity<Media> editDescriptionMedia(@RequestParam(name = "user_id") String user_id, @RequestParam(name = "media_id") Long media_id, @RequestParam(name = "description") String description ) throws JsonProcessingException {
		
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
		if (json.getString("role").equals("Admin")) {
			Media media = mediaService.getMedia(media_id, json.getString("pays")).get();
			media.setDescription(description);
			return ResponseEntity.ok(mediaService.updateMedia(media));
			
		}
		System.out.println("User is not Admin");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@DeleteMapping("/medias/delete/{media_id}/{user_id}")
	public ResponseEntity<Long> deleteMedia(@PathVariable Long media_id, @PathVariable String user_id) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>( headers);
		
		Object objUser = restTemplate.exchange(urlUser+"users/"+user_id, HttpMethod.GET,request,  Object.class).getBody();
		
		String jsonStr = mapper.writeValueAsString(objUser);
		JSONObject json = new JSONObject(jsonStr);
		if (json.getString("role").equals("Admin")) {
			String objPoster = restTemplate.exchange(urlPoster+"posters/delete/"+media_id, HttpMethod.DELETE , request,  String.class).getBody();
			if( Objects.isNull(objPoster)) {
				System.out.println("Api poster return null");
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			System.out.println(objPoster);
		}
		
		return ResponseEntity.ok(mediaService.deleteMedia(media_id));
		 
	}
	
	@PostMapping("/medias/change/status/user/{userRefId}/{userClientid}")
	public ResponseEntity<Object> changeStatusUser(@RequestParam(name = "userRefId") String userRefId, @RequestParam(name = "userClientid") String userClientid, @RequestBody String status) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		JSONObject personJsonObject = new JSONObject();
	    personJsonObject.put("status", status);
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		ResponseEntity<Object> objUser = null;
		try {
			objUser = restTemplate.exchange(urlUser+"users/change/status/"+userRefId+"/"+userClientid, HttpMethod.POST,request,  Object.class);
			System.out.println("status : " + objUser.getStatusCode());
			if ( Objects.isNull(objUser)) {

				System.out.println("Api poster return null");
			   
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		} catch (HttpClientErrorException e) {
			// TODO: handle exception
			System.out.println("in exception");
			System.out.println("execption : "+e.getStatusCode());
			if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}

		}
		
		
		return objUser;
		 
	}
}
