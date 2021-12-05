package com.olx.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.olx.repository.AdvertiseRepository;

//http://localhost:9080/olx-advertise 
//before impl data is null showing like {} only.
@Component
public class AdvertiseCustomInfoActuator implements InfoContributor{

	@Autowired
	AdvertiseRepository advertiseRepository;
	
	@Override
	public void contribute(Builder builder) {
		//builder.withDetail("example", Collections.singletonMap("key","value"));
		//builder.withDetail("information", "Available");
		//builder.withDetail("Total active advertises", "455");
		builder.withDetail("total-active-advertises", advertiseRepository.countOfActiveAdvertises());
		builder.withDetail("total-advertises", advertiseRepository.countOfAdvertises());
	}

}
