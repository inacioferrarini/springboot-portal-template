package com.inacioferrarini.template.portal.sample.security.api.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.inacioferrarini.template.portal.sample.core.api.requests.ApiRequestDto;

public class ForgotUsernameApiRequestDto implements ApiRequestDto {

	@NotEmpty
	@Email
	private final String email;

	public ForgotUsernameApiRequestDto(
		String email
	) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

}
