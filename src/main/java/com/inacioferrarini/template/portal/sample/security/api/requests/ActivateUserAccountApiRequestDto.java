package com.inacioferrarini.template.portal.sample.security.api.requests;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivateUserAccountApiRequestDto implements ApiRequestDto {

	@NotEmpty
	private final String token;

	public ActivateUserAccountApiRequestDto(
		@JsonProperty("token") String token
	) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
