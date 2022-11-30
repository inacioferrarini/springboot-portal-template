package com.inacioferrarini.template.portal.sample.security.api.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.inacioferrarini.template.portal.sample.core.api.requests.ApiRequestDto;
import com.inacioferrarini.template.portal.sample.core.validators.Locale;

public class RegisterAccountApiRequestDto implements ApiRequestDto {

	@Locale(locales = { "en-US", "pt-BR" })
	private String idiom;

	@Length(min = 8, max = 25)
	private final String username;

	@NotEmpty
	@Email
	private final String email;

	@NotEmpty
	@Length(min = 8, max = 25)
	private final String password;

	public RegisterAccountApiRequestDto(
		final String idiom,
		final String username,
		final String email,
		final String password
	) {
		this.idiom = idiom;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getIdiom() {
		return idiom;
	}

	public void setIdiom(String idiom) {
		this.idiom = idiom;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
