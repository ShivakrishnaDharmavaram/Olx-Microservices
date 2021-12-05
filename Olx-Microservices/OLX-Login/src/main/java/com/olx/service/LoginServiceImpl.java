package com.olx.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.User;
import com.olx.entity.UserEntity;
import com.olx.repo.UserRepository;
import com.olx.security.JwtUtil;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtUtil jwtUtil;
	@Override
	public User createNewUser(User user) {
		UserEntity userEntity = this.modelMapper.map(user, UserEntity.class);
		userEntity.setActive("true");
		userEntity.setRoles("ROLE_USER");
		userEntity = this.userRepository.save(userEntity);
		user.setId(userEntity.getId());
		return user;
	}

	@Override
	public User getUserById(int userId) {
		UserEntity userEntity = userRepository.getById(userId);
		User user = this.modelMapper.map(userEntity, User.class);
		return user;
	}

	@Override
	public String getUserName(String authToken) {
		String username = jwtUtil.extractUsername(authToken);
		List<UserEntity> userEntity = userRepository.findByUsername(username);
		User user = this.modelMapper.map(userEntity.get(0), User.class);
		return user.getFirstName() + " " + user.getLastName() + ":" + username;
	}

}
