package com.inacioferrarini.template.portal.sample.todosamplefeature.apis.requests;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inacioferrarini.template.portal.sample.core.api.requests.ApiRequestDto;

public class DeleteManyTodoApiRequestDto implements ApiRequestDto {

	@NotEmpty
	private final List<Long> idList;

	public DeleteManyTodoApiRequestDto(
		@JsonProperty("idList") List<Long> idList
	) {
		this.idList = idList;
	}

	public List<Long> getIdList() {
		return idList;
	}

}
