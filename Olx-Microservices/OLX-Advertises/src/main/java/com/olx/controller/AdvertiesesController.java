package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Advertise;
import com.olx.service.AdvertisesService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/olx/advertise")
public class AdvertiesesController {	
	
	
	
	//9
	@Autowired
	AdvertisesService advertisesService;
	
	
	
	@PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Advertise> createNewAdvertises(@RequestHeader("auth-token")String authToken,
			@RequestBody Advertise advertise) {
		return new ResponseEntity(advertisesService.createNewAdvertise(authToken, advertise),HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Advertise> getAdvertiseBySearchText(@RequestParam("searchText") String searchText) {
		return advertisesService.getAdvertiseBySearchText(searchText);
	}
	@DeleteMapping(value = "/{postId}")
	public ResponseEntity<Boolean> deleteAdvertiseById(@RequestHeader("auth-token") String authToken,
			@PathVariable("postId") int postId) {
		Boolean isDeleted = advertisesService.deleteAdvertiseById(authToken, postId);
		return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
	}
	
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Advertise>> getAllAdvertises(@RequestHeader("auth-token") String authToken) {
		List<Advertise> listOfAdvertise = advertisesService.getAllAdvertises(authToken);
		return new ResponseEntity<List<Advertise>>(listOfAdvertise, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertise> updateAdvertise(@RequestBody Advertise advertise, @RequestHeader("auth-token") String authToken,
			@PathVariable("id") int advertiseId) {
		Advertise responseAdvertise = advertisesService.updateAdvertise(advertise, authToken , advertiseId);
		return new ResponseEntity<Advertise>(responseAdvertise, HttpStatus.OK);
	}

	@GetMapping(value = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertise> getAdvertiseById(@RequestHeader("auth-token") String authToken,
			@PathVariable("postId") int postId) {
		Advertise advertise = advertisesService.getAdvertiseById(authToken, postId);
		return new ResponseEntity<Advertise>(advertise, HttpStatus.OK);
	}
	
	@GetMapping(value = "/user/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertise> getAdvertiseByUserId(@RequestHeader("auth-token") String authToken,
			@PathVariable("postId") int postId) {
		Advertise advertise = advertisesService.getAdvertiseByUserId(authToken, postId);
		return new ResponseEntity<Advertise>(advertise, HttpStatus.OK);

	}
}
