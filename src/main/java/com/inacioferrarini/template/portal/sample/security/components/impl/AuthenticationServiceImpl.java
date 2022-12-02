package com.inacioferrarini.template.portal.sample.security.components.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.inacioferrarini.template.portal.sample.security.components.AuthenticationService;
import com.inacioferrarini.template.portal.sample.security.dtos.ApiUserDto;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Override
	public ApiUserDto getUser() {
		return (ApiUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
