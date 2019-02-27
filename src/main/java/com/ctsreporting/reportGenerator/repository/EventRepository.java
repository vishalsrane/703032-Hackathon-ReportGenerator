package com.ctsreporting.reportGenerator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctsreporting.reportGenerator.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long>{

}
