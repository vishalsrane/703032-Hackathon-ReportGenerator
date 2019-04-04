package com.ctsreporting.reportGenerator.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctsreporting.reportGenerator.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long>{
	
	Event findByEventId(String eventId);
	
	@Query(value="select min(e.EVENTDATE) from event e", nativeQuery = true)
	Date getFirstEventDate();
	
	@Query(value="select max(e.EVENTDATE) from event e", nativeQuery = true)
	Date getLastEventDate();
	
	@Query(value="select distinct(e.base_location) from event e where e.base_location is not null", nativeQuery = true)
	List<String> getAllLocations();
	
}
