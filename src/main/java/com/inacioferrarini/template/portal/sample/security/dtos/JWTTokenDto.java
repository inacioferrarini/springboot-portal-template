package com.inacioferrarini.template.portal.sample.security.dtos;

public class JWTTokenDto {

	public enum TokenType {
		BEARER
	}

	private final String token;
	private final TokenType type;

	public JWTTokenDto(
		String token,
		TokenType type
	) {
		this.token = token;
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public TokenType getType() {
		return type;
	}

}
