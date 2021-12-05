package com.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.entity.CategoryEntity;
import com.olx.entity.StatusEntity;
import com.olx.exception.EmptyListException;
import com.olx.exception.InvalidIdException;
import com.olx.repository.CategoryRepository;
import com.olx.repository.StatusRepository;
//import com.olx.entity.CategoryEntity;
//import com.olx.repository.CategoryRepository;
//import com.olx.utils.MasterDataConverter;

//Connects to RDBMS
@Service//(value = "RDBMS-SERVICE")
public class MasterDataServiceImpl implements MasterDataService {
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Override
	public List<Category> getAllCategories(){
		List<CategoryEntity> categoryEntities = categoryRepository.findAll();
		List<Category> categoriesDtoList = new ArrayList<Category>();
		if (categoryEntities != null && categoryEntities.size() > 0) {

			for(CategoryEntity categoryEntity: categoryEntities) {
				//this. is necessery or not neccs/ because it will take it as implicitly.
				//	TypeMap<CategoryEntity, Category> typeMap=this.modelMapper.typeMap(CategoryEntity.class, Category.class);
				//	typeMap.addMappings((mapper)->{	
				//		mapper.map(source->source.getCategory(), Category::setCategoryName);
				//	});
				Category categoryDto=this.modelMapper.map(categoryEntity, Category.class);
				//Category categoryDto = 	new MasterDataConverter().convertCategoryEntityIntoCategoryDTO(categoryEntity);
				categoriesDtoList.add(categoryDto);			
			}//for example JDBC SQL Code
			return categoriesDtoList;
		}
		else {
			throw new EmptyListException("Categories");
		}
		}

		@Override
		public List<Status> getAllStatus(){
			List<StatusEntity> statusEntities = statusRepository.findAll();
			List<Status> statusesDtoList = new ArrayList<Status>();
			for(StatusEntity statusEntity: statusEntities) {
				Status statusDto=this.modelMapper.map(statusEntity, Status.class);
				/*	Status statusDto = new Status();
			statusDto.setId(statusEntity.getId());
			statusDto.setStatus(statusEntity.getStatus());
				 */

				statusesDtoList.add(statusDto);			
			}//for example JDBC SQL Code
			return statusesDtoList;
		}


		@Override
		public String getStatusNameById(int statusId) {
			String status = statusRepository.findStatusNameById(statusId);
			if (status == null) {
				return "enter correct value";
			} else {
				return status;
			}

		}

		private List<Category> getCategoryDtoList(List<CategoryEntity> categoryEntityList) { 
			List<Category> categoryDtoList = new ArrayList<Category>(); 
			for(CategoryEntity categoryEntity: categoryEntityList) { 
				Category categoryDto = this.modelMapper.map(categoryEntity, Category.class); 
				categoryDtoList.add(categoryDto); } 
			return categoryDtoList; 
		}
		
		@Override
		public Category getCategoryById(int categoryId) {
			Optional<CategoryEntity> entity = categoryRepository.findById(categoryId);
			if (entity.isPresent()) {
				CategoryEntity categoryEntity = entity.get();
				Category category = this.modelMapper.map(categoryEntity, Category.class);
				return category;
			}
			throw new InvalidIdException("category Id : " + categoryId);
		}

		@Override
		public String getCategoryNameById(int categoryId) {
			String categoryName = categoryRepository.findCategoryNameById(categoryId);
			if (categoryName == null) {
				return "Not Mentioned";
			} else {
				return categoryName;
			}
		}

		@Override
		public Integer getStatusIdByName(String statusName) {
			List<Status> statusList = getAllStatus();
			for (Status status : statusList) {
				if(status.getStatus().equals(statusName)) {
					return status.getId();
				}
			}
			return null;
		}
		
		@Override
		public Integer getCategoryIdByName(String categoryName) {
			List<Category> categoryList = getAllCategories();
			for (Category category : categoryList) {
				if(category.getCategory().equals(categoryName)) {
					return category.getId();
				}
			}
			return null;
		}

		public List<Category> findByCategory(String name) {
			List<CategoryEntity> categoryEntityList= categoryRepository.findByCategory(name);
			return getCategoryDtoList(categoryEntityList);
		}


		@Override
		public List<Category> findByDescription(String description) {
			List<CategoryEntity> categoryEntityList= categoryRepository.findByDescription(description);
			return getCategoryDtoList(categoryEntityList);
		}


		private List<Status> getStatusDtoList(List<StatusEntity> statusEntityList) { 
			List<Status> statusDtoList = new ArrayList<Status>(); 
			for(StatusEntity statusEntity: statusEntityList) { 
				Status statusDto = this.modelMapper.map(statusEntity, Status.class); 
				statusDtoList.add(statusDto); } 
			return statusDtoList; 
		}

		@Override
		public List<Status> findByStatus(String status) {
			List<StatusEntity> statusEntityList= statusRepository.findByStatus(status);
			return getStatusDtoList(statusEntityList);
		}




}