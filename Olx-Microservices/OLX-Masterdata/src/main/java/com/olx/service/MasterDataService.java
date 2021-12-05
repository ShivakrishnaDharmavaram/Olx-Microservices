package com.olx.service;

import java.util.ArrayList;
import java.util.List;

import com.olx.dto.Category;
import com.olx.dto.Status;

public interface MasterDataService {
	public List<Category> getAllCategories();
	
	public List<Status> getAllStatus();
	public String getStatusNameById(int statusId);
	public Integer getStatusIdByName(String statusName);

	public Category getCategoryById(int categoryId);
	public String getCategoryNameById(int categoryId);
	public Integer getCategoryIdByName(String categoryName);
	
	public List<Category> findByCategory(String name);
	public List<Category> findByDescription(String description); 
	
	
//	List<Category> findByNameAndDescription(String name, String description); 
	
	public List<Status> findByStatus(String status);
//	List<Status> findByStatusAndId(String status, int Id); 
/*	List<Category> findByNameLike(String name);
	//List<Category> findByOrderByName(String sortType); 
	//List<Category> findByPage(int startIndex, int records);
	
	
	List<Status> findByStatusLike(String status);
	List<Status> findByOrderByStatus(String sortType); 
	List<Status> findByPages(int startIndex, int records);
*/	

}
