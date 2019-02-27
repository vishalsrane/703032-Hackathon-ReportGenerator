package com.ctsreporting.reportGenerator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctsreporting.reportGenerator.model.EventDetails;

@Repository
public interface EventDetailsRepository extends CrudRepository<EventDetails, Long>{
	
}
