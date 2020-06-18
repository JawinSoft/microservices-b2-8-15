package org.spring.boot.msk.mobile.repository;

import java.util.Optional;

import org.spring.boot.msk.mobile.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppRespository extends CrudRepository<AppUser, Integer>{
	
	Optional<AppUser> findByUsername(String userName);

}
