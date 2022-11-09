package com.inacioferrarini.template.portal.sample.security.dto;

public class ApiUserDto {

	private final String username;
	private final JWTTokenDto token;

	public ApiUserDto(
		String username,
		JWTTokenDto token
	) {
		this.username = username;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public JWTTokenDto getToken() {
		return token;
	}

}
