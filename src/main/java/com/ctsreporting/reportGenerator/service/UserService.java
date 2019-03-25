package com.ctsreporting.reportGenerator.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctsreporting.reportGenerator.model.Associate;
import com.ctsreporting.reportGenerator.repository.AssociateRepository;

@Service
public class UserService {
	
	@Autowired
	private AssociateRepository associateRepository;
	
	public List<Associate> getUsers(){
		return associateRepository.findAllUsers(Arrays.asList("POC", "ADMIN"));
	}

	public Associate createUser(Associate associate) {
		return associateRepository.save(associate);
	}

}
