package com.olx.service;

public interface MasterDataDelegate {
	public String getStatusById(int statusId);
	public Integer getStatusIdByName(String statusName);
	public String getCategoryNameById(int categoryId);
	public Integer getCategoryIdByName(String categoryName);

}
