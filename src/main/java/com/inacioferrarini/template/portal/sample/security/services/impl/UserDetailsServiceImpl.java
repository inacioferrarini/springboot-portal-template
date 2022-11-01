package com.inacioferrarini.template.portal.sample.security.services.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("UserDetailsServiceImpl::loadUserByUsername");
		return createUserDetails(username);
	}

	private UserDetails createUserDetails(String username) {
		UserDetails userDetails = User.withUsername(username)
			.build();

		return userDetails;
	}

}
