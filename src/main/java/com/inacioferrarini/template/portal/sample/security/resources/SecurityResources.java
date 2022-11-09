package com.inacioferrarini.template.portal.sample.security.resources;

import com.inacioferrarini.template.portal.sample.samplefeature.controllers.SampleController;

public class SecurityResources {

	public static class Paths {

		public static class Api {

			public static final String ACTIVATE_USER = "/user/activate";
			public static final String AUTHENTICATE = "/auth";
			public static final String FORGOT_USERNAME = "/user/forgotUsername";
			public static final String FORGOT_PASSWORD = "/user/forgotPassword";
			public static final String PASSWORD_RESET = "/user/passwordReset";

		}

		public static class Security {

			public static final String ROOT = "/security";
			public static final String LOGIN = "/login";
			public static final String ACTIVATE_ACCOUNT = "/activateAccount";
			public static final String FORGOT_PASSWORD = "/forgotPassword";
			public static final String FORGOT_USERNAME = "/forgotUsername";
			public static final String PASSWORD_RESET = "/passwordReset";
			public static final String LOGOUT = "/logout";

		}

		public static class Configuration {

			public static final String LOGIN_PAGE = SecurityResources.Paths.Security.ROOT
				.concat(SecurityResources.Paths.Security.LOGIN);
			public static final String LOGIN_PROCESS = SecurityResources.Paths.Security.ROOT
				.concat(SecurityResources.Paths.Security.LOGIN);
			public static final String LOGIN_SUCCESS = SampleController.BASE_MAPPING
				.concat(SampleController.INDEX_MAPPING);
			public static final String LOGOUT = SecurityResources.Paths.Security.ROOT
				.concat(SecurityResources.Paths.Security.LOGOUT);
			public static final String LOGOUT_SUCCESS = SecurityResources.Paths.Security.ROOT
				.concat(SecurityResources.Paths.Security.LOGIN);
			public static final String ACTIVATE_ACCOUNT = SecurityResources.Paths.Security.ROOT
				.concat(SecurityResources.Paths.Security.ACTIVATE_ACCOUNT);
			public static final String FORGOT_PASSWORD = SecurityResources.Paths.Security.ROOT
				.concat(SecurityResources.Paths.Security.FORGOT_PASSWORD);
			public static final String FORGOT_USERNAME = SecurityResources.Paths.Security.ROOT
				.concat(SecurityResources.Paths.Security.FORGOT_USERNAME);
			public static final String PASSWORD_RESET = SecurityResources.Paths.Security.ROOT
				.concat(SecurityResources.Paths.Security.PASSWORD_RESET);

		}

	}

	public static class Views {

		public static final String LOGIN_FORM = "security/login-form";
		public static final String ACTIVATE_ACCOUNT_SUCCESS = "security/activate-account-success";
		public static final String FORGOT_PASSWORD_FORM = "security/forgot-password-form";
		public static final String FORGOT_USERNAME_FORM = "security/forgot-username-form";
		public static final String PASSWORD_RESET_FORM = "security/password-reset-form";

	}

}
