package com.olx.service;

import java.util.List;

import com.olx.dto.Advertise;

public interface AdvertisesService {
	public Advertise createNewAdvertise(String authToken, Advertise advertise);
	public Integer getCountOfAdvertiseByStatus(String statusName);
	
	
	public Advertise updateAdvertise(Advertise advertise, String authToken, int advertiseId);
	public List<Advertise> getAllAdvertises(String authToken);
	public Advertise getAdvertiseByUserPostId(String authToken, int postId);
	public boolean deleteAdvertiseByPostId(String authToken, int postId);
	public List<Advertise> getAdvertiseByCriteria(String searchText, String category, String postedBy,
			String dateCondition, String onDate, String fromDate, String toDate, String sortBy,
			int startIndex, int records);
	public Advertise getAdvertiseByPostId(String authToken, int postId);
	public List<Advertise> getAdvertiseBySearchText(String searchText);

}
