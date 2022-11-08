package com.inacioferrarini.template.portal.sample.security.api.responses;

import com.inacioferrarini.template.portal.sample.security.api.enums.ForgotUsernameStatusType;

public class ForgotUsernameApiResponseDTO {

	private final ForgotUsernameStatusType status;
	private final String message;

	public ForgotUsernameApiResponseDTO(
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
