package com.olx.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertise;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.InvalidIdException;
import com.olx.exception.InvalidUserException;
import com.olx.repository.AdvertiseRepository;

@Service
public class AdvertisesServiceImp implements AdvertisesService {

	@Autowired
	AdvertiseRepository advertiseRepository;

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	MasterDataDelegate masterDataDelegate;

	@Autowired
	LoginDelegate loginDelegate;
	@Autowired
	EntityManager entityManager;


	public Advertise getStatusAndCategoryName(Advertise advertise) {
		String statusName = masterDataDelegate.getStatusById(advertise.getStatus());
		advertise.setStatusName(statusName);
		String categoryName = masterDataDelegate.getCategoryNameById(advertise.getCategory());
		advertise.setCategoryName(categoryName);
		return advertise;
	}

	public String[] getUsername(String authToken) {
		String token = authToken.substring(authToken.indexOf(' ') + 1);
		String usernameWithName = loginDelegate.getUsername(token);
		String[] names = new String[2];
		String name = usernameWithName.substring(0, usernameWithName.indexOf(':'));
		names[0] = name;
		String username = usernameWithName.substring(usernameWithName.indexOf(':') + 1);
		names[1] = username;
		System.out.println("Name : " + name);
		System.out.println("Username : " + username);
		return names;
	}
	@Override
	public Advertise createNewAdvertise(String authToken,Advertise advertise) {
		//call OLX- Log in
		if (!loginDelegate.isValidToken(authToken))
			throw new InvalidUserException(authToken);
		LocalDate currentDate = LocalDate.now();
		advertise.setCreatedDate(currentDate);
		advertise = getStatusAndCategoryName(advertise);
		String[] names = getUsername(authToken);
		advertise.setUsername(names[1]);
		AdvertiseEntity advertiseEntity = this.modelMapper.map(advertise, AdvertiseEntity.class);
		advertiseEntity = this.advertiseRepository.save(advertiseEntity);
		advertise.setId(advertiseEntity.getId());
		return advertise;
		//call OLX- MasterData
	}

	@Override
	public Advertise updateAdvertise(Advertise advertise, String authToken, int advertiseId) {
		if (!loginDelegate.isValidToken(authToken))
			throw new InvalidUserException(authToken);
		Optional<AdvertiseEntity> opAdvertiseEntity = advertiseRepository.findById(advertiseId);
		if (opAdvertiseEntity.isPresent()) {
			AdvertiseEntity advertiseEntity = opAdvertiseEntity.get();
			System.out.println("Advertise Entity : " + advertiseEntity);
			advertise.setId(advertiseId);
			LocalDate currentDate = LocalDate.now();
			advertise.setCreatedDate(advertiseEntity.getCreatedDate());
			advertise.setModifiedDate(currentDate);
			if (advertiseEntity.getUsername() == null)
				advertise.setUsername("Invalid");
			else
				advertise.setUsername(advertiseEntity.getUsername());
			AdvertiseEntity updatedAdvEntity = this.modelMapper.map(advertise, AdvertiseEntity.class);
			updatedAdvEntity.setPostedBy(advertiseEntity.getPostedBy());
			updatedAdvEntity = advertiseRepository.save(updatedAdvEntity);
			Advertise advertiseDto = this.modelMapper.map(updatedAdvEntity, Advertise.class);
			advertiseDto = getStatusAndCategoryName(advertiseDto);
			return advertiseDto;
		}
		throw new InvalidIdException("Advertise Id : " + advertiseId);
	}

	@Override
	public List<Advertise> getAllAdvertises(String authToken) {
		if (!loginDelegate.isValidToken(authToken))
			throw new InvalidUserException(authToken);
		List<AdvertiseEntity> advertiseEntities = advertiseRepository.findAll();
		List<Advertise> advertises = getDtoOfEntityList(advertiseEntities);
		return advertises;
	}

	@Override
	public Advertise getAdvertiseByUserId(String authToken, int postId) {
		if (!loginDelegate.isValidToken(authToken))
			throw new InvalidUserException(authToken);
		Optional<AdvertiseEntity> entity = advertiseRepository.findById(postId);
		if (entity.isPresent()) {
			AdvertiseEntity advertiseEntity = entity.get();
			Advertise advertise = this.modelMapper.map(advertiseEntity, Advertise.class);
			advertise = getStatusAndCategoryName(advertise);
			if (advertiseEntity.getUsername() == null)
				advertise.setUsername("Invalid");
			else
				advertise.setUsername(advertiseEntity.getUsername());
			return advertise;
		}
		throw new InvalidIdException("User Post Id : " + postId);
	}

	@Override
	public boolean deleteAdvertiseById(String authToken, int postId) {
		if (!loginDelegate.isValidToken(authToken))
			throw new InvalidUserException(authToken);
		try {
			advertiseRepository.deleteById(postId);
			return true;
		} catch (Exception exception) {
			throw new InvalidIdException("Post Id : " + postId);
		}
	}


	@Override
	public Advertise getAdvertiseById(String authToken, int postId) {
		if (!loginDelegate.isValidToken(authToken))
			throw new InvalidUserException(authToken);
		Optional<AdvertiseEntity> entity = advertiseRepository.findById(postId);
		if (entity.isPresent()) {
			AdvertiseEntity advertiseEntity = entity.get();
			Advertise advertise = this.modelMapper.map(advertiseEntity, Advertise.class);
			advertise = getStatusAndCategoryName(advertise);
			if (advertiseEntity.getUsername() == null)
				advertise.setUsername("Invalid");
			else
				advertise.setUsername(advertiseEntity.getUsername());
			return advertise;
		}
		throw new InvalidIdException("invalid: " + postId);
	}

	@Override
	public List<Advertise> getAdvertiseBySearchText(String searchText) {
		List<AdvertiseEntity> list1 = advertiseRepository.findByTitleLike(searchText);
		List<AdvertiseEntity> list2 = advertiseRepository.findByDescriptionLike(searchText);
		List<AdvertiseEntity> advertises = new ArrayList<AdvertiseEntity>();
		advertises.addAll(list1);
		advertises.addAll(list2);
		List<AdvertiseEntity> distinctAdvertises = advertises.stream() 
				.distinct() // removes duplicates
				.collect(Collectors.toList());
		List<Advertise> searchedAdvertises = getDtoOfEntityList(distinctAdvertises);
		return searchedAdvertises;
	}

	@Override
	public Integer getCountOfAdvertiseByStatus(String statusName) {
		Integer statusId = masterDataDelegate.getStatusIdByName(statusName);
		return advertiseRepository.countOfAdvertisesByStatus(statusId);
	}

	public List<Advertise> getDtoOfEntityList(List<AdvertiseEntity> advertisesEntities) {
		List<Advertise> advertises = new ArrayList<Advertise>();
		for (AdvertiseEntity advertiseEntity : advertisesEntities) {
			Advertise advertise = this.modelMapper.map(advertiseEntity, Advertise.class);
			advertise = getStatusAndCategoryName(advertise);
			if (advertiseEntity.getUsername() == null)
				advertise.setUsername("Anonymous");
			else
				advertise.setUsername(advertiseEntity.getUsername());
			advertises.add(advertise);
		}
		return advertises;
	}

	


}	

