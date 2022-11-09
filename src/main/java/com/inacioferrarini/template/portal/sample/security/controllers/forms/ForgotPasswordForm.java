package com.inacioferrarini.template.portal.sample.security.controllers.forms;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ForgotPasswordForm {

	@NotEmpty
	@Length(min = 8, max = 25)
	private String username;

	public ForgotPasswordForm() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
