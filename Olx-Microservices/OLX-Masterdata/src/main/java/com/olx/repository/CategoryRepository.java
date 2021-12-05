package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olx.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>{
	
	List<CategoryEntity> findByCategory(String name);
	List<CategoryEntity> findByDescription(String description);
	//List<CategoryEntity> findByNameAndDescription(String name, String description); 
	
//	@Query(value ="SELECT ce from CategoryEntity ce WHERE ce.name LIKE %:name%")
//	List<CategoryEntity> findByNameLike(String name);

	//List<CategoryEntity> findByOrderByNameAsc(); 
	//List<CategoryEntity> findByOrderByNameDesc();
	
	//List<CategoryEntity> findByPage(int startIndex, int records);
	
	
}
