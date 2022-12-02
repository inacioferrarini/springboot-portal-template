package com.inacioferrarini.template.portal.sample.core.api.factories.impl;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.inacioferrarini.template.portal.sample.core.api.factories.ApiRequestFactory;
import com.inacioferrarini.template.portal.sample.core.api.requests.ApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.dtos.JWTTokenDto;

@Component
public class ApiRequestFactoryImpl implements ApiRequestFactory {

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	private HttpHeaders getHeaders(JWTTokenDto token) {
		HttpHeaders headers = getHeaders();
		headers.setBearerAuth(token.getToken());
		return headers;
	}

	@Override
	public HttpEntity<String> emptyRequestEntity() {
		HttpHeaders headers = getHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>("parameters", headers);
		return requestEntity;
	}

	@Override
	public HttpEntity<String> emptyRequestEntity(JWTTokenDto token) {
		HttpHeaders headers = getHeaders(token);
		HttpEntity<String> requestEntity = new HttpEntity<>("parameters", headers);
		return requestEntity;
	}

	@Override
	public <T extends ApiRequestDto> HttpEntity<T> requestEntity(T request) {
		HttpHeaders headers = getHeaders();
		HttpEntity<T> requestEntity = new HttpEntity<>(
			request, headers
		);
		return requestEntity;
	}

	@Override
	public <T extends ApiRequestDto> HttpEntity<T> requestEntity(
		T request,
		JWTTokenDto token
	) {
		HttpHeaders headers = getHeaders(token);
		HttpEntity<T> requestEntity = new HttpEntity<>(
			request, headers
		);
		return requestEntity;
	}

}
