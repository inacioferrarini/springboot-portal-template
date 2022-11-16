package com.inacioferrarini.template.portal.sample.core.api.errors.exceptions;

import com.inacioferrarini.template.portal.sample.security.errors.ApiError;

public class FieldValueLengthInvalidException implements ApiError {

	private final String message;

	public FieldValueLengthInvalidException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
