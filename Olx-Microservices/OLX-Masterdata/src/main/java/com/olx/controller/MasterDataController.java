package com.olx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.service.MasterDataService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/olx/masterdata")
public class MasterDataController {

	@Autowired
	//@Qualifier("RDBMS-SERVICE")
	private MasterDataService masterDataService;
	static ArrayList<Category> categories = new ArrayList<Category>();
	static ArrayList<Status> statusList = new ArrayList<Status>();


	@GetMapping(value = "/advertise/category", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value="This REST endpoint is to get all categories")
	public List<Category> getAllCategories(){
		return this.masterDataService.getAllCategories();
		//new ArrayList<Category>();
	}	
	
	@GetMapping(value = "/advertise/status", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value="This REST endpoint is to get all status")
	public List<Status> getAllStatus(){
		return this.masterDataService.getAllStatus();
		//ArrayList<Status>();
	}
	
	@GetMapping(value = "/advertise/status/{id}", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value="This REST endpoint is to get status name by Id")
	public String getStatusNameById(@PathVariable("id") int statusId) {
		return masterDataService.getStatusNameById(statusId);
	}
	
	@GetMapping(value = "/advertise/category/{id}", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value="This REST endpoint is to get category by Id")
	public Category getCategoryById(@PathVariable("id") int categoryId) {
		return masterDataService.getCategoryById(categoryId);
	}
	
	@GetMapping(value = "/advertise/category/name/{id}", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value="This REST endpoint is to get category name by Id")
	public String getCategoryNameById(@PathVariable("id") int categoryId) {
		return masterDataService.getCategoryNameById(categoryId);
	}
	
	@GetMapping(value = "/advertise/statusId/{statusName}", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value="This REST endpoint is to get status Id by Name")
	public Integer getStatusIdByName(@PathVariable("statusName") String statusName) {
		return masterDataService.getStatusIdByName(statusName);
	}
	
	@GetMapping(value = "/advertise/categoryId/{categoryName}", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value="This REST endpoint is to get category Id by Name")
	public Integer getCategoryIdByName(@PathVariable("categoryName") String categoryName) {
		return masterDataService.getCategoryIdByName(categoryName);
	}
}
/*
	@GetMapping(value="/advertise/status/{id}")
	public String getStatusById(@PathVariable("id") int statusId) {
		if(statusId==1) {
			return "OPEN";
		}
		else{
			return "CLOSED";
		}

	}



	
	 * @GetMapping(value="/category/{name}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public List<Category> getCategoryByName(@PathVariable("name") String name) { 
		return masterDataService.findByCategory(name); } 

	@GetMapping(value="/category/description/{description}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public List<Category> getCategoryByDescription(@PathVariable("description") String desc) { 
		return masterDataService.findByDescription(desc); } 




	//status

	@GetMapping(value="/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public List<Status> getStatusByName(@PathVariable("status") String status) { 
		return masterDataService.findByStatus(status); } 
	@GetMapping(value="/category/description/{description}/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public List<Category> getCategoryByNameAndDescription(@PathVariable("name") String name, @PathVariable("description") String desc) { 
		return masterDataService.findByNameAndDescription(name,desc); } 

	@GetMapping(value="/status/id/{id}/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public List<Status> getStocksByNameAndId(@PathVariable("status") String status, @PathVariable("id") int id) { 
		return masterDataService.findByStatusAndId(status,id); } 


	@GetMapping(value="/category/name/like/{name}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public List<Category> getCategoryByNameLike(@PathVariable("name") String name) { 
		return masterDataService.findByNameLike(name); } 

	@GetMapping(value="/category/name/sort/{sortType}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public List<Category> getCategoryOrderByName(@PathVariable("sortType") String sortType) { 
		return masterDataService.findByOrderByName(sortType); } 

	@GetMapping(value="/category/page/{startIndex}/{records}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public List<Category> getCategoryByPage(@PathVariable("startIndex") int startIndex, @PathVariable("records") int records) { 
		return masterDataService.findByPage(startIndex, records); }





	@GetMapping(value="/status/status/like/{status}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public List<Status> getStatusByStatusLike(@PathVariable("status") String status) { 
		return masterDataService.findByStatusLike(status); } 

	@GetMapping(value="/status/status/sort/{sortType}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public List<Status> getStatusOrderByStatus(@PathVariable("sortType") String sortType) { 
		return masterDataService.findByOrderByStatus(sortType); } 

	@GetMapping(value="/status/page/{startIndex}/{records}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public List<Status> getStatusByPage(@PathVariable("startIndex") int startIndex, @PathVariable("records") int records) { 
		return masterDataService.findByPages(startIndex, records); }


	 */




