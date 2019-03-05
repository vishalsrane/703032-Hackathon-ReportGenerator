package com.ctsreporting.reportGenerator.controller;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctsreporting.reportGenerator.dto.AssociateByBusinessunitDto;
import com.ctsreporting.reportGenerator.model.Associate;
import com.ctsreporting.reportGenerator.model.Event;
import com.ctsreporting.reportGenerator.model.EventDetails;
import com.ctsreporting.reportGenerator.repository.AssociateRepository;
import com.ctsreporting.reportGenerator.repository.EventDetailsRepository;
import com.ctsreporting.reportGenerator.repository.EventRepository;
import com.ctsreporting.reportGenerator.service.ChartService;
import com.ctsreporting.reportGenerator.service.ProcessDataService;

@RestController
public class DataLoadController {
	
	@Autowired
	private AssociateRepository associateRepository;
	
	@Autowired
	private ProcessDataService processDataService;
	
	@Autowired
	private EventDetailsRepository eventDetailsRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private ChartService chartService;
	
	@GetMapping("/getAssociate")
	public Iterable<Associate> getAssociate() {
//		Associate associate = new Associate();
//		associate.setAssociateId(713032l);
//		associate.setName("Vishal Rane");
//		associate.setDesignation("Software Engineer");
//		associate.setBusinessUnit("Retail");
//		associateRepository.save(associate);
		processDataService.processUpdatedExcels();
//		Event event = new Event();
//		event.setEventId("event 1");
//		eventRepository.save(event);
//		EventDetails eventDetails = new EventDetails();
//		eventDetails.setAssociate(associate);
//		eventDetails.setEvent(event);
//		eventDetailsRepository.save(eventDetails);
//		EventDetails eventDetailsfromDb = eventDetailsRepository.findAssociateIdAndEventId(713032l, "event 1");
//		return eventDetailsfromDb;
		return associateRepository.findAll();
		
	}
	
	@GetMapping("/countByBusinessUnit")
	public Map<String, Long> getAssociateCountByBusinessUnit() {
		
		return chartService.getAssociateCountByBusinessUnit();
	}

}
