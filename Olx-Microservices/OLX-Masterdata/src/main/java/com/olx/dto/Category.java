package com.olx.dto;

import lombok.Data;

//@Data I sometimes got lombok error, maybe be due to STS, so I completely impletented this dto class.
public class Category {
	private int id;
	private String category;
	//private String categoryName;
	//private String description;
	public Category(int id, String category) {
		super();
		this.id = id;
		this.category = category;
	}
	public Category() {}
	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	//public void setCategoryName(String categoryName)
//	{
//		this.categoryName=categoryName;
	//}

}
