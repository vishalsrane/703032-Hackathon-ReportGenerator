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
@Data
public class EventDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long volunteerHours;
	private Long travelHours;
	
	@ManyToOne
	@JoinColumn(name="ASSOCIATE_ID", nullable=false)
	private Associate associate;
	
	@ManyToOne
	@JoinColumn(name="EVENT_ID", nullable=false)
	private Event event;

}
