package com.inacioferrarini.template.portal.sample.security.api.factories;

import org.springframework.http.HttpEntity;

import com.inacioferrarini.template.portal.sample.security.api.requests.AuthenticateRequestDTO;

public interface ApiRequestFactory {

	HttpEntity<AuthenticateRequestDTO> createAuthenticationRequestEntity(AuthenticateRequestDTO authenticateRequest);
	
}
