
package org.spring.boot.msk.mobile.service;

import java.util.Optional;

import org.spring.boot.msk.mobile.model.AppUser;
import org.spring.boot.msk.mobile.repository.AppRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private AppRespository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<AppUser> dbUser = repository.findByUsername(username);

		if (dbUser.isPresent()) {
			return User.builder().username(dbUser.get().getUsername()).password(dbUser.get().getPassword())
					.disabled(!dbUser.get().getActive()).roles(dbUser.get().getRoles()).build();
		}

		throw new UsernameNotFoundException("User Not Found : " + username);

	}

}
