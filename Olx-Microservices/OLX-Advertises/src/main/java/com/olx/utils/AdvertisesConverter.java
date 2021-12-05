package com.olx.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.olx.dto.Advertise;
import com.olx.entity.AdvertiseEntity;

public class AdvertisesConverter {

	@Autowired
	ModelMapper modelMapper;
	
	public Advertise ConvertCategoryEntityToCategoryDto(AdvertiseEntity advertiseEntity) {
		Advertise advertise = this.modelMapper.map(advertiseEntity,Advertise.class);
		return advertise;
	}
}
