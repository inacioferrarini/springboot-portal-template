package com.inacioferrarini.template.portal.sample.security.api.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ForgotUsernameApiRequestDTO implements ApiRequestDto {

	@NotEmpty
	@Email
	private final String email;

	public ForgotUsernameApiRequestDTO(
		String email
	) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

}
