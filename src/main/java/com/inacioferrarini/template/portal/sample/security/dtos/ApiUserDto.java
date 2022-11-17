package com.inacioferrarini.template.portal.sample.security.dtos;

import java.util.Locale;

public class ApiUserDto {

	private final String username;
	private final Locale idiom;
	private final JWTTokenDto token;

	public ApiUserDto(
		String username,
		Locale idiom,
		JWTTokenDto token
	) {
		this.username = username;
		this.idiom = idiom;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public Locale getIdiom() {
		return idiom;
	}

	public JWTTokenDto getToken() {
		return token;
	}

}
