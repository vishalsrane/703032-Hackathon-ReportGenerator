package com.ctsreporting.reportGenerator.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctsreporting.reportGenerator.model.Associate;

@Repository
public interface AssociateRepository extends CrudRepository<Associate, Long>{
	
	List<Associate> findByName(String name);
	
	Associate findByAssociateId(Long associateId);
}
