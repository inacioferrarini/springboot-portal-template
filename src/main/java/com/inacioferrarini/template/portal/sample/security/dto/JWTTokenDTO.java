package com.inacioferrarini.template.portal.sample.security.dto;

public class JWTTokenDTO {

	public enum TokenType {
		BEARER
	}

	private final String token;
	private final TokenType type;

	public JWTTokenDTO(
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
