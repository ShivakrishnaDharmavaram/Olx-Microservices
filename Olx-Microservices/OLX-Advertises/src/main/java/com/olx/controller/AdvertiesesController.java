package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Advertise;
import com.olx.service.AdvertisesService;

@RestController
@RequestMapping("/olx/advertise")
public class AdvertiesesController {	
	
	
	
	//9
	@Autowired
	AdvertisesService advertisesService;
	
	
	
	@PostMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertise> createNewAdvertises(@RequestHeader("auth-token")String authToken,
			@RequestBody Advertise advertise) {
		return new ResponseEntity(advertisesService.createNewAdvertise(authToken, advertise),HttpStatus.CREATED);
	}
	/*
	
	@GetMapping(value="/advertises/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Advertise getAdvertiseById(@PathVariable("id") int advertiseId) {
		return advertisesService.getAdvertiseById(advertiseId);
	}
	
	@GetMapping(value="/advertises", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Advertise> getAllAdvertises(){
		return advertisesService.getAllAdvertises();
				//new ArrayList<Advertise>();//
		}
	
	@DeleteMapping(value="/advertises")
	public boolean deleteAllAdvertises() {
		return advertisesService.deleteAllAdvertises();
	}
	
	@DeleteMapping(value="/advertises/{id}")
	public boolean deleteAdvertiseById(@PathVariable("id") int advertiseId) {
		return advertisesService.deleteAdvertiseById(advertiseId);
	}
	
	@PutMapping(value="/advertises/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Advertise updateAdvertises(@PathVariable("id") int advertiseId, @RequestBody Advertise advertise) {
		return advertisesService.updateAdvertise(advertiseId, advertise);
	}
	/*
	//7
	static List<Advertise> advertises= new ArrayList<Advertise>();
	private int LastAdvertiseId;
	@PostMapping(value="/advertise", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Advertise CreateNewAdvertise(@RequestBody Advertise advertise, @RequestHeader("auth-token") String authToken) {
		System.out.println("Auth Token :" +authToken);
		advertise.setId(++LastAdvertiseId);
		advertises.add(advertise);
		return advertise;
	}

	//8 Updating by using PUT Method
	@PostMapping(value="/advertise/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Advertise updateAdvertise(@PathVariable("id") int advertiseId, @RequestBody Advertise advertise)
	{
		for(Advertise advertise1:advertises)
		{
			if(advertise1.getId()==advertiseId)
			{
				advertise1.setTitle(advertise.getTitle());
				advertise1.setPrice(advertise1.getPrice());
				advertise1.setCategory(advertise1.getCategory());
				advertise1.setDescription(advertise1.getDescription());
				advertise1.setUsername(advertise1.getUsername());
				advertise1.setCategoryName(advertise1.getCategoryName());
				advertise1.setCreatedDate(advertise1.getCreatedDate());
				advertise1.setStatus(advertise1.getStatus());
				advertise1.setStatusName(advertise1.getStatusName());
				advertise1.setModifiedDate(advertise1.getModifiedDate());
				
				return advertise1;
				
			}
		}
		return null;
		}
	*/
	}
