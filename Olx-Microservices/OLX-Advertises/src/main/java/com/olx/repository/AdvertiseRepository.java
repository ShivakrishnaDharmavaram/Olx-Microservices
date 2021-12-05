package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olx.entity.AdvertiseEntity;

public interface AdvertiseRepository extends JpaRepository<AdvertiseEntity, Integer> {

	@Query(value="select * from advertises where title like %:searchText%", nativeQuery = true)
	List<AdvertiseEntity> findByTitleLike(@Param("searchText")String searchText);
	
	@Query(value="select * from advertises where description like %:searchText%", nativeQuery = true)
	List<AdvertiseEntity> findByDescriptionLike(@Param("searchText")String searchText);
	
	@Query(value="SELECT COUNT(id) FROM advertises", nativeQuery = true)
	Integer countOfAdvertises();
	
	@Query(value="SELECT COUNT(id) FROM advertises where status=:statusId", nativeQuery = true)
	Integer countOfAdvertisesByStatus(int statusId);
	
	@Query(value="select count(id) from advertises where posted_by=:username and active=:active", nativeQuery = true)
	Integer countOfAdvertisesByUsernameAndActive(String username, String active);
	
	@Query(value="select count(id) from advertises where active='1'", nativeQuery = true)
	Integer countOfActiveAdvertises();
}
