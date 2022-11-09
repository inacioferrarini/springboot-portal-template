package com.inacioferrarini.template.portal.sample.security.api.responses;

import com.inacioferrarini.template.portal.sample.security.api.enums.ForgotPasswordStatusType;

public class ForgotPasswordApiResponseDto {

	private final ForgotPasswordStatusType status;
	private final String message;

	public ForgotPasswordApiResponseDto(
		ForgotPasswordStatusType status,
		String message
	) {
		this.status = status;
		this.message = message;
	}

	public ForgotPasswordStatusType getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
