package com.ctsreporting.reportGenerator.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ctsreporting.reportGenerator.model.EventDetails;

@Repository
public interface EventDetailsRepository extends CrudRepository<EventDetails, Long>{
	
//	@Query("from EventDetails ed where ed.ASSOCIATE_ID=:associateId and ed.EVENT_ID=:eventId")
//	public Iterable<EventDetails> findAssociateIdAndEventId(@Param("associateId") Long associateId, @Param("eventId") String eventId );
	
	@Query("from EventDetails ed where ed.associate.associateId=:associateId and ed.event.eventId=:eventId")
	public EventDetails findAssociateIdAndEventId(@Param("associateId") Long associateId, @Param("eventId") String eventId);
	
	
}
