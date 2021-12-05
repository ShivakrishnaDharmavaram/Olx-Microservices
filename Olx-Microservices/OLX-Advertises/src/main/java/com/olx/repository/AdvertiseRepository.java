package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olx.entity.AdvertiseEntity;

public interface AdvertiseRepository extends JpaRepository<AdvertiseEntity, Integer> {

	List<AdvertiseEntity> findByTitleLike(String searchText);
	
	List<AdvertiseEntity> findByDescriptionLike(String searchText);
	
	Integer countOfAdvertises();
	
	Integer countOfAdvertisesByStatus(int statusId);
	
	Integer countOfAdvertisesByUsernameAndActive(String username, String active);
	
	Integer countOfActiveAdvertises();
}
