package com.inacioferrarini.template.portal.sample.security.api.requests;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.inacioferrarini.template.portal.sample.core.api.requests.ApiRequestDto;

public class PasswordResetApiRequestDto implements ApiRequestDto {

	@NotEmpty
	private final String token;

	@NotEmpty
	@Length(min = 8, max = 25)
	private final String newPassword;

	public PasswordResetApiRequestDto(
		String token,
		String newPassword
	) {
		this.token = token;
		this.newPassword = newPassword;
	}

	public String getToken() {
		return token;
	}

	public String getNewPassword() {
		return newPassword;
	}

}
