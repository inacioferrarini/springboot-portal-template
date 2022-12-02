package com.inacioferrarini.template.portal.sample.todosamplefeature.apis.requests;

import com.inacioferrarini.template.portal.sample.core.api.requests.ApiRequestDto;
import com.inacioferrarini.template.portal.sample.todosamplefeature.dtos.TodoDto.StatusType;

public class TodoDataApiRequestDto implements ApiRequestDto {

	private final String name;
	private final String description;
	private final StatusType status;

	public TodoDataApiRequestDto(
		final String name,
		final String description,
		final StatusType status
	) {
		this.name = name;
		this.description = description;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public StatusType getStatus() {
		return status;
	}

}
