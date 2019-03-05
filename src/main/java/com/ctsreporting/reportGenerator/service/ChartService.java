package com.ctsreporting.reportGenerator.service;

import java.util.Iterator;
import java.util.Map;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctsreporting.reportGenerator.dto.AssociateByBusinessunitDto;
import com.ctsreporting.reportGenerator.model.Associate;
import com.ctsreporting.reportGenerator.repository.AssociateRepository;

@Service
public class ChartService {
	
	@Autowired
	private AssociateRepository associateRepository;
	
    public Map<String, Long> getAssociateCountByBusinessUnit() {
    	Iterable<Associate> associates = associateRepository.findAll();
    	Map<String, Long> numberOfStudentsByCountry = StreamSupport.stream(associates.spliterator(), false)
    	             .collect(groupingBy(Associate::getBusinessUnit, counting()));
		return numberOfStudentsByCountry;
	}

}
