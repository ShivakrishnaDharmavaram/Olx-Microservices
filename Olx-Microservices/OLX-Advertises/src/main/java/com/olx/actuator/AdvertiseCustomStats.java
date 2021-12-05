package com.olx.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.olx.repository.AdvertiseRepository;
import com.olx.service.AdvertisesService;

@Component
@Endpoint(id = "advertiseStat")
public class AdvertiseCustomStats {

	@Autowired
	AdvertiseRepository advertiseRepository;

	@Autowired
	AdvertisesService advertisesService;

	@ReadOperation
	public String getCount() {
		return "Count Of Advertises : " + advertiseRepository.countOfAdvertises();
	}

	@ReadOperation
	public String getCountByStatus(@Selector String statusName) {
		return "Count Of Advertises : " + advertisesService.getCountOfAdvertiseByStatus(statusName);
	}

	@ReadOperation()
	public String getCountByUsername(@Selector String userName, @Selector String active) {
		return "Count of Advertises by user " + userName + " that are active : "
				+ advertiseRepository.countOfAdvertisesByUsernameAndActive(userName, active);
	}

}
