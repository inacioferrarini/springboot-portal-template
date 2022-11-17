package com.inacioferrarini.template.portal.sample.security.handlers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.inacioferrarini.template.portal.sample.core.messages.GlobalMessageHelper;
import com.inacioferrarini.template.portal.sample.security.api.constants.ApiMessageKeys;
import com.inacioferrarini.template.portal.sample.security.errors.exceptions.CompoundSecurityException;
import com.inacioferrarini.template.portal.sample.security.errors.exceptions.InvalidUserCredentialsException;
import com.inacioferrarini.template.portal.sample.security.errors.exceptions.UserPendingActivationException;
import com.inacioferrarini.template.portal.sample.security.resources.SecurityResources;

public class ApiAuthenticationFailureHandler implements AuthenticationFailureHandler {

	public ApiAuthenticationFailureHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	private MessageSource messageSource;

	@Override
	public void onAuthenticationFailure(
		final HttpServletRequest request,
		final HttpServletResponse response,
		final AuthenticationException exception
	) throws IOException, ServletException {
		final String loginUrl = getLoginUrl(request);

		Optional.of(exception)
			.filter(CompoundSecurityException.class::isInstance)
			.map(CompoundSecurityException.class::cast)
			.ifPresent(apiException -> {
				final String message = getStringFromBundle(ApiMessageKeys.INVALID_CREDENTIALS);
				GlobalMessageHelper.setGlobalErrorMessage(message, request, response);
			});

		Optional.of(exception)
			.filter(InvalidUserCredentialsException.class::isInstance)
			.map(InvalidUserCredentialsException.class::cast)
			.ifPresent(apiException -> {
				final String message = getStringFromBundle(ApiMessageKeys.INVALID_CREDENTIALS);
				GlobalMessageHelper.setGlobalErrorMessage(message, request, response);
			});

		Optional.of(exception)
			.filter(UserPendingActivationException.class::isInstance)
			.map(UserPendingActivationException.class::cast)
			.ifPresent(apiException -> {
				final String message = getStringFromBundle(ApiMessageKeys.ACTIVATION_PENDING);
				GlobalMessageHelper.setGlobalErrorMessage(message, request, response);
			});

		response.sendRedirect(loginUrl);
	}

	private String getLoginUrl(final HttpServletRequest request) {
		return request.getServletContext() + SecurityResources.Paths.Configuration.LOGIN_PAGE;
	}

	private String getStringFromBundle(final String messageKey) {
		return messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
	}

}
