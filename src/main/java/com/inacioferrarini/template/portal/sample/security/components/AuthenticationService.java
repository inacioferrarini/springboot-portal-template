package com.inacioferrarini.template.portal.sample.security.components;

import com.inacioferrarini.template.portal.sample.security.dtos.ApiUserDto;

public interface AuthenticationService {

	ApiUserDto getUser();
	
}
