package com.olx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class CategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  int id;
	
	@Column(name = "name")
	private  String category;

	public CategoryEntity()
	{}
	
	public CategoryEntity(int id, String category, String description) {
		super();
		this.id = id;
		this.category = category;
	}
	
	public CategoryEntity(String category, String description) {
		super();
		this.category = category;
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



	@Override
	public String toString() {
		return "CategoryEntity [id=" + id + ", category=" + category + ", desc="+ "]";
	}

}
