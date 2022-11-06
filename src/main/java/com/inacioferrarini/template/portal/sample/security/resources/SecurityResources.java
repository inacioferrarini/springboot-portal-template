package com.inacioferrarini.template.portal.sample.security.resources;

import com.inacioferrarini.template.portal.sample.samplefeature.controllers.SampleController;

public class SecurityResources {

	public static class Paths {

		public static class Api {

			public static final String ACTIVATE_USER = "/user/activate";
			public static final String AUTHENTICATE = "/auth";

		}

		public static class Security {

			public static final String ROOT = "/security";
			public static final String LOGIN = "/login";
			public static final String ACTIVATE_ACCOUNT = "/activateAccount";
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

		}

	}

	public static class Views {

		public static final String LOGIN = "security/login";
		public static final String ACTIVATE_ACCOUNT_SUCCESS = "security/activate-account-success";

	}

}
