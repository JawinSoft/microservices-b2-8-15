package org.spring.boot.msk.mobile.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="jpa_users")
public class AppUser {
	
	@Id
	private Integer id;
	
	private String username;
	
	private String password;
	
	private Boolean active;
	
	private String roles;

}
