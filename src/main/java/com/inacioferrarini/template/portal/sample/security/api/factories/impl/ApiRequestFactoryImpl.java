package com.inacioferrarini.template.portal.sample.security.api.factories.impl;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.inacioferrarini.template.portal.sample.security.api.factories.ApiRequestFactory;
import com.inacioferrarini.template.portal.sample.security.api.requests.ActivateUserAccountRequestDTO;
import com.inacioferrarini.template.portal.sample.security.api.requests.AuthenticateRequestDTO;
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
		HttpEntity<AuthenticateRequestDTO>
		createAuthenticationRequestEntity(AuthenticateRequestDTO authenticateRequestDTO) {
		HttpHeaders headers = getHeaders();
		HttpEntity<AuthenticateRequestDTO> requestEntity = new HttpEntity<>(authenticateRequestDTO, headers);
		return requestEntity;
	}

	@Override
	public
		HttpEntity<ActivateUserAccountRequestDTO>
		createActivateAccountRequestEntity(ActivateUserAccountRequestDTO activateAccountRequestDTO) {
		HttpHeaders headers = getHeaders();
		HttpEntity<ActivateUserAccountRequestDTO> requestEntity = new HttpEntity<>(activateAccountRequestDTO, headers);
		return requestEntity;
	}

}
