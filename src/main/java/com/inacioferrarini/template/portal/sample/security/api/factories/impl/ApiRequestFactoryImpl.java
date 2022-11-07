package com.inacioferrarini.template.portal.sample.security.api.factories.impl;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.inacioferrarini.template.portal.sample.security.api.factories.ApiRequestFactory;
import com.inacioferrarini.template.portal.sample.security.api.requests.ActivateUserAccountApiRequestDTO;
import com.inacioferrarini.template.portal.sample.security.api.requests.AuthenticateApiRequestDTO;
import com.inacioferrarini.template.portal.sample.security.api.requests.ForgotUsernameApiRequestDTO;
import com.inacioferrarini.template.portal.sample.security.dto.JWTTokenDTO;

@Component
public class ApiRequestFactoryImpl implements ApiRequestFactory {

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	private HttpHeaders getHeaders(JWTTokenDTO tokenDTO) {
		HttpHeaders headers = getHeaders();
		headers.setBearerAuth(tokenDTO.getToken());
		return headers;
	}

	@Override
	public
		HttpEntity<AuthenticateApiRequestDTO>
		createAuthenticationApiRequestEntity(AuthenticateApiRequestDTO authenticateApiRequestDTO) {
		HttpHeaders headers = getHeaders();
		HttpEntity<AuthenticateApiRequestDTO> requestEntity = new HttpEntity<>(authenticateApiRequestDTO, headers);
		return requestEntity;
	}

	@Override
	public
		HttpEntity<ActivateUserAccountApiRequestDTO>
		createActivateAccountApiRequestEntity(ActivateUserAccountApiRequestDTO activateAccountApiRequestDTO) {
		HttpHeaders headers = getHeaders();
		HttpEntity<ActivateUserAccountApiRequestDTO> requestEntity = new HttpEntity<>(
			activateAccountApiRequestDTO, headers
		);
		return requestEntity;
	}

	@Override
	public
		HttpEntity<ForgotUsernameApiRequestDTO>
		createForgotUsernameApiRequestEntity(ForgotUsernameApiRequestDTO forgotUsernameApiRequestDTO) {
		HttpHeaders headers = getHeaders();
		HttpEntity<ForgotUsernameApiRequestDTO> requestEntity = new HttpEntity<>(
			forgotUsernameApiRequestDTO, headers
		);
		return requestEntity;
	}

}
