package com.inacioferrarini.template.portal.sample.security.api.responses;

import com.inacioferrarini.template.portal.sample.security.api.enums.RegisterAccountStatusType;

public class RegisterAccountApiResponseDto {

	private final RegisterAccountStatusType status;
	private final String message;

	public RegisterAccountApiResponseDto(
		final RegisterAccountStatusType status,
		final String message
	) {
		this.status = status;
		this.message = message;
	}

	public RegisterAccountStatusType getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
