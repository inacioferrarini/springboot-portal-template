package com.inacioferrarini.template.portal.sample.security.api.requests;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivateUserAccountRequestDTO {

	@NotEmpty
	private final String token;

	public ActivateUserAccountRequestDTO(
		@JsonProperty("token") String token
	) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
