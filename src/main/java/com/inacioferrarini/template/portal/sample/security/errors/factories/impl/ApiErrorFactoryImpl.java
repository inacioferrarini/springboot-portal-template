package com.inacioferrarini.template.portal.sample.security.errors.factories.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inacioferrarini.template.portal.sample.core.api.errors.dtos.ApiErrorMessageDto;
import com.inacioferrarini.template.portal.sample.security.errors.ApiError;
import com.inacioferrarini.template.portal.sample.security.errors.exceptions.InvalidUserCredentialsException;
import com.inacioferrarini.template.portal.sample.security.errors.exceptions.UserPendingActivationException;
import com.inacioferrarini.template.portal.sample.security.errors.factories.ApiErrorFactory;

@Component
public class ApiErrorFactoryImpl implements ApiErrorFactory {

	private static final String INVALID_USER_CREDENTIALS = "API-SEC-001-0001";
	private static final String USER_PENDING_ACTIVATION = "API-SEC-001-0002";
	private static final String INVALID_TOKEN = "API-SEC-001-0003";
	private static final String EXPIRED_TOKEN = "API-SEC-001-0004";

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public Optional<ApiError> parseException(BadRequest badRequestException) {

		Optional<ApiError> exception = Optional.empty();

		try {
			ApiErrorMessageDto errorMessage = objectMapper.readValue(
				badRequestException.getResponseBodyAsString(),
				ApiErrorMessageDto.class
			);

			switch (errorMessage.getCode()) {
			case INVALID_USER_CREDENTIALS:
				exception = Optional.of(new InvalidUserCredentialsException(errorMessage.getMessage()));
				break;
			case USER_PENDING_ACTIVATION:
				exception = Optional.of(new UserPendingActivationException(errorMessage.getMessage()));
				break;
			case INVALID_TOKEN:
				break;
			case EXPIRED_TOKEN:
				break;
			}
		} catch (JsonProcessingException e) {
			// TODO: Log as debug
		}

		return exception;
	}

}
