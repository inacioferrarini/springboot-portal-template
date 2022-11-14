package com.inacioferrarini.template.portal.sample.security.errors.exceptions;

import org.springframework.security.core.AuthenticationException;

import com.inacioferrarini.template.portal.sample.security.errors.ApiError;

public class InvalidUserCredentialsException extends AuthenticationException implements ApiError {

	private static final long serialVersionUID = 1L;

	public InvalidUserCredentialsException(String msg) {
		super(msg);
	}

}
