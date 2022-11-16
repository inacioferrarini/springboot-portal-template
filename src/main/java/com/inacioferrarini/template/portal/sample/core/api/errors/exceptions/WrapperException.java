package com.inacioferrarini.template.portal.sample.core.api.errors.exceptions;

import java.util.List;

import com.inacioferrarini.template.portal.sample.security.errors.ApiError;

public class WrapperException implements ApiError {

	private final List<ApiError> exceptions;

	public WrapperException(
		final List<ApiError> exceptions
	) {
		this.exceptions = exceptions;
	}

	public List<ApiError> getExceptions() {
		return exceptions;
	}

}
