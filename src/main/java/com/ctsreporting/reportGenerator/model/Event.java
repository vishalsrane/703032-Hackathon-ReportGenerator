package com.ctsreporting.reportGenerator.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String eventId;
	
	private String month; //TODO
	
	private String baseLocation;
	
	private String benificiaryname;
	
	private String venue;
	
	private String counsil;
	
	private String project;
	
	private String category;
	
	private String name;
	
	private String description;
	
	private Date eventdate;
	
	private Long volunteersCount;
	
	private Long volunteersHours;
	
	private Long volunteersTravelHours;
	
	private Long livesImpacted;
	
	private String activityType;
	
	private String status;
	
	@OneToMany
	private Set<Associate> poc = new HashSet<>();
	
	@OneToMany(mappedBy = "event")
	private Set<EventDetails> eventDetails;
}
