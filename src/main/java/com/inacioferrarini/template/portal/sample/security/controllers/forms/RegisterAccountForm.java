package com.inacioferrarini.template.portal.sample.security.controllers.forms;

import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.inacioferrarini.template.portal.sample.core.validators.Locale;

public class RegisterAccountForm {

	private List<java.util.Locale> supportedIdiomList;

	@Locale(locales = { "en-US", "pt-BR" })
	private String idiom;

	@NotEmpty
	@Length(min = 8, max = 25)
	private String username;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	@Length(min = 8, max = 25)
	private String password;

	@NotEmpty
	@Length(min = 8, max = 25)
	private String passwordConfirmation;

	@NotNull
	@AssertTrue
	private Boolean agreedToTerms;

	public RegisterAccountForm() {
	}

	public List<java.util.Locale> getSupportedIdiomList() {
		return supportedIdiomList;
	}

	public void setSupportedIdiomList(List<java.util.Locale> supportedIdiomList) {
		this.supportedIdiomList = supportedIdiomList;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Boolean getAgreedToTerms() {
		return agreedToTerms;
	}

	public void setAgreedToTerms(Boolean agreedToTerms) {
		this.agreedToTerms = agreedToTerms;
	}

}
