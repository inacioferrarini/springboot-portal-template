package com.inacioferrarini.template.portal.sample.security.controllers.forms;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class PasswordResetForm {

	@NotEmpty
	private String token;

	@NotEmpty
	@Length(min = 8, max = 25)
	private String newPassword;

	public PasswordResetForm() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
