package com.inacioferrarini.template.portal.sample.security.errors.factories.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inacioferrarini.template.portal.sample.core.api.errors.dtos.ApiErrorMessageDto;
import com.inacioferrarini.template.portal.sample.core.api.errors.dtos.ApiErrorMessageListDto;
import com.inacioferrarini.template.portal.sample.core.api.errors.exceptions.FieldValueLengthInvalidException;
import com.inacioferrarini.template.portal.sample.core.api.errors.exceptions.RequiredFieldValueEmptyException;
import com.inacioferrarini.template.portal.sample.core.api.errors.exceptions.WrapperException;
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
	
	public static final String IS_REQUIRED = "API-GEN-001-0001";
	public static final String INVALID_LENGTH = "API-GEN-001-0002";
	public static final String MUST_BE_UNIQUE = "API-GEN-001-0003";
	public static final String UNKOWN = "API-GEN-001-9999";

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public Optional<ApiError> parseException(BadRequest badRequestException) {
		final String json = badRequestException.getResponseBodyAsString();

		Optional<ApiErrorMessageDto> error = parseError(json);
		if (error.isPresent()) {
			return map(error.get());
		}

		Optional<ApiErrorMessageListDto> errorList = parseErrorList(json);
		if (errorList.isPresent()) {
			List<ApiError> apiErrorList = errorList.get()
				.getErrorList().stream().map(this::map)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
			return Optional.of(new WrapperException(apiErrorList));
		}

		return Optional.empty();
	}

	private Optional<ApiErrorMessageDto> parseError(final String json) {
		try {
			return Optional.of(
				objectMapper.readValue(json, ApiErrorMessageDto.class)
			);
		} catch (JsonProcessingException e) {
			return Optional.empty();
		}
	}

	private Optional<ApiErrorMessageListDto> parseErrorList(final String json) {
		try {
			List<ApiErrorMessageDto> errorList = Arrays.asList(
				objectMapper.readValue(json, ApiErrorMessageDto[].class)
			);
			return Optional.of(
				new ApiErrorMessageListDto(errorList)
			);
		} catch (JsonProcessingException e) {
			return Optional.empty();
		}
	}

	private Optional<ApiError> map(final ApiErrorMessageDto errorMessage) {
		Optional<ApiError> exception = Optional.empty();

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
		case IS_REQUIRED:
			exception = Optional.of(new RequiredFieldValueEmptyException(errorMessage.getMessage()));
			break;
		case INVALID_LENGTH:
			exception = Optional.of(new FieldValueLengthInvalidException(errorMessage.getMessage()));
			break;
		case MUST_BE_UNIQUE:
			break;
		case UNKOWN:
			break;
		}

		return exception;
	}

}
