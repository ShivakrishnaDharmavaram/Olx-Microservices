package com.olx.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.olx.repo.UserRepository;

@Component
public class CustomLoginInfoActuator implements InfoContributor {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void contribute(Builder builder) {
		builder.withDetail("total-active-users", userRepository.countOfActiveUsers());
		builder.withDetail("total-users", userRepository.countOfUsers());
	}

}
