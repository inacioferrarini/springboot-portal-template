package com.inacioferrarini.template.portal.sample.security.dto;

public class ApiUserDTO {

	private final String username;
	private final JWTTokenDTO token;

	public ApiUserDTO(
		String username,
		JWTTokenDTO token
	) {
		this.username = username;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public JWTTokenDTO getToken() {
		return token;
	}

}
