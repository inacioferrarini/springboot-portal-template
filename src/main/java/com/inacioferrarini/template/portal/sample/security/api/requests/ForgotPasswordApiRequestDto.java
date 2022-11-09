package com.inacioferrarini.template.portal.sample.security.api.requests;

import org.hibernate.validator.constraints.Length;

public class ForgotPasswordApiRequestDto implements ApiRequestDto {

	@Length(min = 8, max = 25)
	private final String username;

	public ForgotPasswordApiRequestDto(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
