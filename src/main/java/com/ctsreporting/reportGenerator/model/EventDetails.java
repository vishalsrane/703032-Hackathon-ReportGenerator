package com.ctsreporting.reportGenerator.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Table(
	   uniqueConstraints = @UniqueConstraint(columnNames={"ASSOCIATE_ID", "EVENT_ID"})
)
@Entity
//@Data
public class EventDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVolunteerHours() {
		return volunteerHours;
	}

	public void setVolunteerHours(Long volunteerHours) {
		this.volunteerHours = volunteerHours;
	}

	public Long getTravelHours() {
		return travelHours;
	}

	public void setTravelHours(Long travelHours) {
		this.travelHours = travelHours;
	}

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	private Long volunteerHours;
	private Long travelHours;
	
	@ManyToOne
	@JoinColumn(name="ASSOCIATE_ID", nullable=false)
	private Associate associate;
	
	@ManyToOne
	@JoinColumn(name="EVENT_ID", nullable=false)
	private Event event;

}
