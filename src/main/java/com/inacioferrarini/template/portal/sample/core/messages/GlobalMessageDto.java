package com.inacioferrarini.template.portal.sample.core.messages;

public class GlobalMessageDto {
	
	public enum Type {
		SUCCESS, ERROR;
	}

	private final String message;
	private final Type type;

	public GlobalMessageDto(
		String message,
		Type type
	) {
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public Type getType() {
		return type;
	}

}
