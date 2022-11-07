package com.inacioferrarini.template.portal.sample.security.api.requests;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class AuthenticateApiRequestDTO {

	@NotEmpty
	@Length(min = 8, max = 25)
	private final String username;

	@NotEmpty
	@Length(min = 8, max = 25)
	private final String password;

	public AuthenticateApiRequestDTO(
		String username,
		String password
	) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
