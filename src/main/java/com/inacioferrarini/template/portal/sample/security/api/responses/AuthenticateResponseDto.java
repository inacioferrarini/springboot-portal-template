package com.inacioferrarini.template.portal.sample.security.api.responses;

import com.inacioferrarini.template.portal.sample.security.dtos.JWTTokenDto;

public class AuthenticateResponseDto {

	private final JWTTokenDto token;
	private final String idiom;

	public AuthenticateResponseDto(
		JWTTokenDto token,
		String idiom
	) {
		this.token = token;
		this.idiom = idiom;
	}

	public JWTTokenDto getToken() {
		return token;
	}

	public String getIdiom() {
		return idiom;
	}

}
