package com.ctsreporting.reportGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctsreporting.reportGenerator.model.Associate;
import com.ctsreporting.reportGenerator.repository.AssociateRepository;
import com.ctsreporting.reportGenerator.service.ProcessDataService;

@RestController
public class DataLoadController {
	
	@Autowired
	private AssociateRepository associateRepository;
	
	@Autowired
	private ProcessDataService processDataService;
	
	@GetMapping("/getAssociate")
	public Associate getAssociate() {
//		Associate associate = new Associate();
//		associate.setAssociateId("713032");
//		associate.setName("Vishal Rane");
//		associate.setDesignation("Software Engineer");
//		associate.setBusinessUnit("Retail");
//		associateRepository.save(associate);
//		Associate associate1 = new Associate();
//		associate1.setAssociateId("7030235");
//		associateRepository.save(associate1);
		processDataService.processUpdatedExcels();
		return associateRepository.findByAssociateId(7030235l);
	}

}
