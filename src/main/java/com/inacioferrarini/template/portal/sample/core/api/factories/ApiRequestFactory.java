package com.inacioferrarini.template.portal.sample.core.api.factories;

import org.springframework.http.HttpEntity;

import com.inacioferrarini.template.portal.sample.core.api.requests.ApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.dtos.JWTTokenDto;

public interface ApiRequestFactory {

	HttpEntity<String> emptyRequestEntity();

	HttpEntity<String> emptyRequestEntity(JWTTokenDto token);

	<T extends ApiRequestDto> HttpEntity<T> requestEntity(T request);

	<T extends ApiRequestDto> HttpEntity<T> requestEntity(
		T request,
		JWTTokenDto token
	);

}
