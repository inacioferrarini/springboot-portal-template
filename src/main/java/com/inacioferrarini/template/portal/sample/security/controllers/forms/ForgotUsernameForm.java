package com.inacioferrarini.template.portal.sample.security.controllers.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ForgotUsernameForm {

	@NotEmpty
	@Email
	private String email;

	public ForgotUsernameForm() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
