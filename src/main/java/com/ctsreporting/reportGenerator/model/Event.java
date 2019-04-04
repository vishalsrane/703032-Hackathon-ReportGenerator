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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//@Data
public class Event {
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getBaseLocation() {
		return baseLocation;
	}

	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}

	public String getBenificiaryname() {
		return benificiaryname;
	}

	public void setBenificiaryname(String benificiaryname) {
		this.benificiaryname = benificiaryname;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getCouncil() {
		return council;
	}

	public void setCouncil(String council) {
		this.council = council;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEventdate() {
		return eventdate;
	}

	public void setEventdate(Date eventdate) {
		this.eventdate = eventdate;
	}

	public Long getVolunteersCount() {
		return volunteersCount;
	}

	public void setVolunteersCount(Long volunteersCount) {
		this.volunteersCount = volunteersCount;
	}

	public Long getVolunteersHours() {
		return volunteersHours;
	}

	public void setVolunteersHours(Long volunteersHours) {
		this.volunteersHours = volunteersHours;
	}

	public Long getVolunteersTravelHours() {
		return volunteersTravelHours;
	}

	public void setVolunteersTravelHours(Long volunteersTravelHours) {
		this.volunteersTravelHours = volunteersTravelHours;
	}

	public Long getLivesImpacted() {
		return livesImpacted;
	}

	public void setLivesImpacted(Long livesImpacted) {
		this.livesImpacted = livesImpacted;
	}

	public Long getActivityType() {
		return activityType;
	}

	public void setActivityType(Long activityType) {
		this.activityType = activityType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Associate> getPoc() {
		return poc;
	}

	public void setPoc(Set<Associate> poc) {
		this.poc = poc;
	}

	public Set<EventDetails> getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(Set<EventDetails> eventDetails) {
		this.eventDetails = eventDetails;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String eventId;
	
	private String month; //TODO
	
	private String baseLocation;
	
	private String benificiaryname;
	
	private String venue;
	
	private String council;
	
	private String project;
	
	private String category;
	
	private String name;
	
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date eventdate;
	
	private Long volunteersCount;
	
	private Long volunteersHours;
	
	private Long volunteersTravelHours;
	
	private Long livesImpacted;
	
	private Long activityType;
	
	private String status;
	
	@OneToMany
	private Set<Associate> poc = new HashSet<>();
	
	@OneToMany(mappedBy = "event")
	private Set<EventDetails> eventDetails;
}
