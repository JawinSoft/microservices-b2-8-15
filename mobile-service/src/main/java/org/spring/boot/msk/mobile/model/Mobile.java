package org.spring.boot.msk.mobile.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NamedQuery(name =  "Mobile.findAllWithFiltersByNamedQuery", query = "select m from Mobile m where (:name is null or m.name = :name)" + 
															" and (:price is null or m.price= :price)" + 
															" and (:status is null or m.status = :status)" + 
															"and (:lob is null or m.lob = :lob)")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mobile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private Integer price;
	
	private Status status;	
	
	private LOB lob;
	
	@Column(name = "country_code")
	private String countryCode;
	
	private LocalDate publicationDate;
	
}
