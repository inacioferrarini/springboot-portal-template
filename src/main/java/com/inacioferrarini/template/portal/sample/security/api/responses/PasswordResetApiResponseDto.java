package com.inacioferrarini.template.portal.sample.security.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inacioferrarini.template.portal.sample.security.api.enums.PasswordResetStatusType;

public class PasswordResetApiResponseDto {

	private final PasswordResetStatusType status;

	public PasswordResetApiResponseDto(
		@JsonProperty("status") PasswordResetStatusType status
	) {
		this.status = status;
	}

	public PasswordResetStatusType getStatus() {
		return status;
	}

}
