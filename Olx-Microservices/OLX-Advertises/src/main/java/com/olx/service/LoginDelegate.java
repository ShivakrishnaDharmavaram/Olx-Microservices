package com.olx.service;

public interface LoginDelegate {
	
	public boolean isValidToken(String authToken);
	public String getUsername(String authToken);
}
