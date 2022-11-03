package com.inacioferrarini.template.portal.sample.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(SecurityController.BASE_MAPPING)
public class SecurityController {

	public static final String BASE_MAPPING = "/security";
	public static final String LOGIN_MAPPING = "/login";
	public static final String LOGOUT_MAPPING = "/logout";

	private static final String LOGIN_VIEW = "security/login";

	@GetMapping(LOGIN_MAPPING)
	public String login() {
		return LOGIN_VIEW;
	}

}
