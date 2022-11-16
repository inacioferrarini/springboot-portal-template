package com.inacioferrarini.template.portal.sample.security.errors.exceptions;

import java.util.List;

import org.springframework.security.core.AuthenticationException;

import com.inacioferrarini.template.portal.sample.security.errors.ApiError;

public class CompoundSecurityException extends AuthenticationException implements ApiError {

	private static final long serialVersionUID = 1L;

	private final List<ApiError> exceptions;

	public CompoundSecurityException(
		String msg,
		List<ApiError> exceptions
	) {
		super(msg);
		this.exceptions = exceptions;
	}

	public List<ApiError> getExceptions() {
		return exceptions;
	}

}
