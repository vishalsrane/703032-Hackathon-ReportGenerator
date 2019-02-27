package com.ctsreporting.reportGenerator.model;

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
public class Associate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private Long associateId;
	
	private String name;
	
	private String designation;
	
	private String location;
	
	private String businessUnit;
	
	private String contactNumber;
	
	@OneToMany(mappedBy = "associate")
	private Set<EventDetails> eventDetails;

}
