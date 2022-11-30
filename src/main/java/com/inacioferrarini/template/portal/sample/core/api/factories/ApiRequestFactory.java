package com.inacioferrarini.template.portal.sample.core.api.factories;

import org.springframework.http.HttpEntity;

import com.inacioferrarini.template.portal.sample.core.api.requests.ApiRequestDto;

public interface ApiRequestFactory {

	<T extends ApiRequestDto> HttpEntity<T> requestEntity(T request);

}
