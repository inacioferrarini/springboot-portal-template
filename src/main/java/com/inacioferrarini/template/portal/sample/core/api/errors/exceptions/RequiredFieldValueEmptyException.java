package com.inacioferrarini.template.portal.sample.core.api.errors.exceptions;

import com.inacioferrarini.template.portal.sample.security.errors.ApiError;

public class RequiredFieldValueEmptyException implements ApiError {

	private final String message;

	public RequiredFieldValueEmptyException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
