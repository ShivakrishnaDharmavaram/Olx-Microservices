package com.olx.service;

import java.util.List;

import com.olx.dto.Advertise;

public interface AdvertisesService {
	public Advertise createNewAdvertise(String authToken, Advertise advertise);
	public Integer getCountOfAdvertiseByStatus(String statusName);
	
	
	public Advertise updateAdvertise(Advertise advertise, String authToken, int advertiseId);
	public List<Advertise> getAllAdvertises(String authToken);
	public Advertise getAdvertiseByUserId(String authToken, int postId);
	public boolean deleteAdvertiseById(String authToken, int postId);
	public Advertise getAdvertiseById(String authToken, int postId);
	public List<Advertise> getAdvertiseBySearchText(String searchText);

}
