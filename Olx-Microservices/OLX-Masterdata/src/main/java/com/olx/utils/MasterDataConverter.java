package com.olx.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.Category;
import com.olx.entity.CategoryEntity;

@Service
public class MasterDataConverter {

	@Autowired
	ModelMapper modelMapper;
	//static
	//you should not never write into dto service impl class
	public Category convertCategoryEntityIntoCategoryDTO(CategoryEntity categoryEntity) {
		Category categoryDto=this.modelMapper.map(categoryEntity, Category.class);
		return categoryDto;

	}

}


/* singleton part
private static MasterDataConverter masterDataConverter;

public static MasterDataConverter getInstance() {
	if(masterDataConverter ==null) {
		masterDataConverter=new MasterDataConverter();
	}
	return masterDataConverter;
}
 */
