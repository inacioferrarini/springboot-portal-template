package com.inacioferrarini.template.portal.sample.security.handlers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.inacioferrarini.template.portal.sample.core.messages.GlobalMessageHelper;
import com.inacioferrarini.template.portal.sample.security.errors.exceptions.InvalidUserCredentialsException;
import com.inacioferrarini.template.portal.sample.security.errors.exceptions.UserPendingActivationException;
import com.inacioferrarini.template.portal.sample.security.resources.SecurityResources;

public class ApiAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(
		final HttpServletRequest request,
		final HttpServletResponse response,
		final AuthenticationException exception
	) throws IOException, ServletException {
		final String loginUrl = getLoginUrl(request);

		Optional.of(exception)
			.filter(InvalidUserCredentialsException.class::isInstance)
			.map(InvalidUserCredentialsException.class::cast)
			.ifPresent(apiException -> {
				// TODO: Get from bundle
				GlobalMessageHelper.setGlobalErrorMessage("Invalid Credentials", request, response);
			});

		Optional.of(exception)
			.filter(UserPendingActivationException.class::isInstance)
			.map(UserPendingActivationException.class::cast)
			.ifPresent(apiException -> {
				// TODO: Get from bundle
				GlobalMessageHelper.setGlobalErrorMessage("User Pending Activation", request, response);
			});

		response.sendRedirect(loginUrl);
	}

	private String getLoginUrl(HttpServletRequest request) {
		return request.getServletContext() + SecurityResources.Paths.Configuration.LOGIN_PAGE;
	}

}
