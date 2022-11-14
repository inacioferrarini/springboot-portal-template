package com.inacioferrarini.template.portal.sample.security.errors.exceptions;

import org.springframework.security.core.AuthenticationException;

import com.inacioferrarini.template.portal.sample.security.errors.ApiError;

public class UserPendingActivationException extends AuthenticationException implements ApiError {

	private static final long serialVersionUID = 1L;

	public UserPendingActivationException(String msg) {
		super(msg);
	}

}
