package com.ctsreporting.reportGenerator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ctsreporting.reportGenerator.model.Associate;

@Repository
public interface AssociateRepository extends CrudRepository<Associate, Long>{
	
	List<Associate> findByName(String name);
	
	Associate findByAssociateId(Long associateId);
	
	@Query(value="select count(*) from associate a join event_details ad on a.id = ad.associate_id", nativeQuery = true)
	Long associatesCount();
	
	@Query("from Associate a where a.role in ?1")
	List<Associate> findAllUsers(@Param("roles") List<String> roles);
	
	@Query(value="select distinct(a.business_unit) from associate a where a.business_unit is not null", nativeQuery = true)
	List<String> getAllBusinessUnits();
}
