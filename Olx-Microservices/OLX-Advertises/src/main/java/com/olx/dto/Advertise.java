package com.olx.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;

public class Advertise {

	private int id;
	private String title;
	private double price;
	private int category;
	private String categoryName;
	private String description;
	private String username;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private int status;
	private String statusName;

	public Advertise() {	}

	public Advertise(int id, String title, double price, int category, String categoryName, String description,
			String username, LocalDate createdDate, LocalDate modifiedDate, int status, String statusName) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.category = category;
		this.categoryName = categoryName;
		this.description = description;
		this.username = username;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.statusName = statusName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "Advertise [id=" + id + ", title=" + title + ", price=" + price + ", category=" + category
				+ ", categoryName=" + categoryName + ", description=" + description + ", username=" + username
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", status=" + status
				+ ", statusName=" + statusName + "]";
	}

	

}
