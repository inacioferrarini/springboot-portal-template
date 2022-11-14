package com.inacioferrarini.template.portal.sample.core.api.errors.dtos;

public class ApiErrorMessageDto {

	private final String code;
	private final String message;

	public ApiErrorMessageDto(
		String code,
		String message
	) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
