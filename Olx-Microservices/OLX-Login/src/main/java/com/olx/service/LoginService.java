package com.olx.service;

import org.springframework.stereotype.Service;

import com.olx.dto.User;

@Service
public interface LoginService {

	public User createNewUser(User user);
	public User getUserById(int userId);
	public String getUserName(String authToken);
}
