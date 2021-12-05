package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olx.entity.StatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer>{
	
	List<StatusEntity> findByStatus(String status);
	
	@Query("SELECT s.status FROM StatusEntity s where s.id = :id") 
    String findStatusNameById(@Param("id") int id);
	
//	List<StatusEntity> findByStatusAndId(String status, int Id); 
	/*	
	@Query(value ="SELECT se from StatusEntity se WHERE se.status LIKE %:status%")
	List<StatusEntity> findByStatusLike(String status);
	
	List<StatusEntity> findByOrderByNameAsc(); 
	List<StatusEntity> findByOrderByNameDesc();

	List<StatusEntity> findByPages(int startIndex, int records);
*/
}
