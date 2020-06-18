package org.spring.boot.msk.mobile.repository;

import java.util.List;

import org.spring.boot.msk.mobile.model.LOB;
import org.spring.boot.msk.mobile.model.Mobile;
import org.spring.boot.msk.mobile.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileRepository extends JpaRepository<Mobile, Integer>, JpaSpecificationExecutor<Mobile>{

	   //JPQL - Named Parameters
	   @Query("select m from Mobile m where (:name is null or m.name = :name)"+
			    " and (:price is null or m.price= :price) "+
				" and (:status is null or m.status = :status) "+
				" and (:lob is null or m.lob = :lob)")
		List<Mobile> findAll(String name, Integer price, Status status, LOB lob);
		
		//JPQL - PlaceHolders
		@Query("select m from Mobile m where (?1 is null or m.name = ?1)"+
			    " and (?2 is null or m.price= ?2) "+
				" and (?3 is null or m.status = ?3) "+
				" and (?4 is null or m.lob = ?4)")
		List<Mobile> findAll(String name, Integer price, String status, String lob);
		
		//Native Query
		@Query(value =  "select * from Mobile m where (:name is null or m.name = :name)"+
			    " and (:price is null or m.price= :price) "+
				" and (:status is null or m.status = :status) "+
				" and (:lob is null or m.lob = :lob)" , nativeQuery = true)
		List<Mobile> findAllByNativeQuery(String name, Integer price, Integer status, Integer lob);
	
	
	    List<Mobile> findAllWithFiltersByNamedQuery(String name, Integer price, Status status, LOB lob);
	
	

	    
}
