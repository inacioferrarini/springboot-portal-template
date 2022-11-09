package com.inacioferrarini.template.portal.sample.security.api.responses;

import com.inacioferrarini.template.portal.sample.security.api.enums.ForgotUsernameStatusType;

public class ForgotUsernameApiResponseDto {

	private final ForgotUsernameStatusType status;
	private final String message;

	public ForgotUsernameApiResponseDto(
		ForgotUsernameStatusType status,
		String message
	) {
		this.status = status;
		this.message = message;
	}

	public ForgotUsernameStatusType getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
