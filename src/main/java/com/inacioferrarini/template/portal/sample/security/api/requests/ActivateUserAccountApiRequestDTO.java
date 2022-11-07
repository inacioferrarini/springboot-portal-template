package com.inacioferrarini.template.portal.sample.security.api.requests;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivateUserAccountApiRequestDTO {

	@NotEmpty
	private final String token;

	public ActivateUserAccountApiRequestDTO(
		@JsonProperty("token") String token
	) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
