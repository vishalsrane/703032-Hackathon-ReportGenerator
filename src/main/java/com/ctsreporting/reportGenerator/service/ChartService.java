package com.ctsreporting.reportGenerator.service;

import java.util.Map;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctsreporting.reportGenerator.dto.ParticipationMatrics;
import com.ctsreporting.reportGenerator.model.Associate;
import com.ctsreporting.reportGenerator.repository.AssociateRepository;
import com.ctsreporting.reportGenerator.repository.EventRepository;

@Service
public class ChartService {
	
	@Autowired
	private AssociateRepository associateRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
    public Map<String, Long> getAssociateCountByBusinessUnit() {
    	Iterable<Associate> associates = associateRepository.findAll();
    	Map<String, Long> numberOfStudentsByCountry = StreamSupport.stream(associates.spliterator(), false)
    	             .collect(groupingBy(Associate::getBusinessUnit, counting()));
		return numberOfStudentsByCountry;
	}
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public ParticipationMatrics participationMatrics(Date fromDate, Date toDate, String location, String bu) {
    	ParticipationMatrics participationMatrics = new ParticipationMatrics();
    	fromDate = eventRepository.getFirstEventDate();
    	toDate = eventRepository.getLastEventDate();
    	List<String> allLocations = eventRepository.getAllLocations();
    	List<String> allBunessUnits = associateRepository.getAllBusinessUnits();
    	participationMatrics.setFromDate(fromDate);
    	participationMatrics.setToDate(toDate);
    	participationMatrics.setLocations(allLocations);
    	participationMatrics.setBusinessUnits(allBunessUnits);
    	return participationMatrics;
    }

}
