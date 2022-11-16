package com.inacioferrarini.template.portal.sample.core.api.errors.dtos;

import java.util.List;

public class ApiErrorMessageListDto {

	private final List<ApiErrorMessageDto> errorList;

	public ApiErrorMessageListDto(List<ApiErrorMessageDto> errorList) {
		this.errorList = errorList;
	}

	public List<ApiErrorMessageDto> getErrorList() {
		return errorList;
	}

}
