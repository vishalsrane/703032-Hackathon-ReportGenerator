package com.ctsreporting.reportGenerator.dto;

import java.util.Date;
import java.util.List;

public class ParticipationMatrics {
	
	private Date fromDate;
	
	private Date toDate;
	
	private List<String> businessUnits;
	
	private List<String> locations;
	
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public List<String> getBusinessUnits() {
		return businessUnits;
	}

	public void setBusinessUnits(List<String> businessUnits) {
		this.businessUnits = businessUnits;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}
	
}
