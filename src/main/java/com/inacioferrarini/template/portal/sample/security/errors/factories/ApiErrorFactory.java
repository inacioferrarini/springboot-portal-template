package com.inacioferrarini.template.portal.sample.security.errors.factories;

import java.util.Optional;

import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.inacioferrarini.template.portal.sample.security.errors.ApiError;

public interface ApiErrorFactory {

	Optional<ApiError> parseException(BadRequest badRequestException);
	
}
