package com.inacioferrarini.template.portal.sample.security.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.inacioferrarini.template.portal.sample.core.api.errors.exceptions.UnexpectedValueException;
import com.inacioferrarini.template.portal.sample.core.messages.UserMessageHelper;
import com.inacioferrarini.template.portal.sample.security.api.responses.ActivateUserAccountApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.ForgotPasswordApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.ForgotUsernameApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.PasswordResetApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.services.SecurityApiService;
import com.inacioferrarini.template.portal.sample.security.controllers.forms.ForgotPasswordForm;
import com.inacioferrarini.template.portal.sample.security.controllers.forms.ForgotUsernameForm;
import com.inacioferrarini.template.portal.sample.security.controllers.forms.PasswordResetForm;
import com.inacioferrarini.template.portal.sample.security.resources.SecurityResources;

@Controller
@RequestMapping(SecurityResources.Paths.Security.ROOT)
public class SecurityController {

	private static final String TOKEN_KEY = "token";

	static final String MSG_ACTIVATE_ACCOUNT_SUCCESS = "message.security.account.activation.success";
	static final String MSG_FORGOT_PASSWORD = "message.security.account.forgotPassword";
	static final String MSG_FORGOT_USERNAME = "message.security.account.forgotUsername";
	static final String MSG_PASSWORD_RESET_SUCCESS = "message.security.account.passwordResetSuccess";

	@Autowired
	private SecurityApiService api;

	@Autowired
	private MessageSource messageSource;

	@GetMapping(SecurityResources.Paths.Security.LOGIN)
	public String showLoginPage(
		HttpServletRequest request
	) {
		return SecurityResources.Views.LOGIN_FORM;
	}

	@GetMapping(SecurityResources.Paths.Security.ACTIVATE_ACCOUNT)
	public ModelAndView activateAccount(
		@RequestParam(name = TOKEN_KEY) String token,
		RedirectAttributes redirectAttributes
	) {
		try {
			ResponseEntity<ActivateUserAccountApiResponseDto> apiResponse = api.activateAccount(token);
			ensure(apiResponse.getStatusCode(), HttpStatus.OK);

			UserMessageHelper.setGlobalSuccessMessage(
				getMessage(MSG_ACTIVATE_ACCOUNT_SUCCESS),
				redirectAttributes
			);
		} catch (BadRequest exception) {
			UserMessageHelper.setGlobalErrorMessage(
				exception.getResponseBodyAsString(),
				redirectAttributes
			);
		}

		return new ModelAndView(new RedirectView(SecurityResources.Paths.Configuration.LOGIN_PAGE, true));
	}

	@GetMapping(SecurityResources.Paths.Security.FORGOT_USERNAME)
	public String showForgotUsernamePage(
		ForgotUsernameForm form
	) {
		form.setEmail("");
		return SecurityResources.Views.FORGOT_USERNAME_FORM;
	}

	@PostMapping(SecurityResources.Paths.Security.FORGOT_USERNAME)
	public ModelAndView sendForgotUsername(
		@Valid ForgotUsernameForm form,
		BindingResult result,
		RedirectAttributes redirectAttributes,
		HttpServletRequest request
	) {
		if (result.hasErrors()) {
			return new ModelAndView(SecurityResources.Views.FORGOT_USERNAME_FORM);
		}

		try {
			ResponseEntity<ForgotUsernameApiResponseDto> apiResponse = api.forgotUsername(
				form.getEmail()
			);
			ensure(apiResponse.getStatusCode(), HttpStatus.OK);

			UserMessageHelper.setGlobalSuccessMessage(
				getMessage(MSG_FORGOT_USERNAME),
				redirectAttributes
			);
		} catch (BadRequest exception) {
			UserMessageHelper.setGlobalErrorMessage(
				exception.getResponseBodyAsString(),
				redirectAttributes
			);
			return new ModelAndView(SecurityResources.Views.FORGOT_USERNAME_FORM);
		}

		return new ModelAndView(new RedirectView(SecurityResources.Paths.Configuration.LOGIN_PAGE, true));
	}

	@GetMapping(SecurityResources.Paths.Security.FORGOT_PASSWORD)
	public String showForgotPasswordPage(
		ForgotPasswordForm form
	) {
		form.setUsername("");
		return SecurityResources.Views.FORGOT_PASSWORD_FORM;
	}

	@PostMapping(SecurityResources.Paths.Security.FORGOT_PASSWORD)
	public ModelAndView sendForgotPassword(
		@Valid ForgotPasswordForm form,
		BindingResult result,
		RedirectAttributes redirectAttributes
	) {
		if (result.hasErrors()) {
			return new ModelAndView(SecurityResources.Views.FORGOT_PASSWORD_FORM);
		}

		try {
			ResponseEntity<ForgotPasswordApiResponseDto> apiResponse = api.forgotPassword(
				form.getUsername()
			);
			ensure(apiResponse.getStatusCode(), HttpStatus.OK);

			UserMessageHelper.setGlobalSuccessMessage(
				getMessage(MSG_FORGOT_PASSWORD),
				redirectAttributes
			);
		} catch (BadRequest exception) {
			UserMessageHelper.setGlobalErrorMessage(
				exception.getResponseBodyAsString(),
				redirectAttributes
			);
			return new ModelAndView(SecurityResources.Views.FORGOT_PASSWORD_FORM);
		}

		return new ModelAndView(new RedirectView(SecurityResources.Paths.Configuration.LOGIN_PAGE, true));
	}

	@GetMapping(SecurityResources.Paths.Security.PASSWORD_RESET)
	public String showPasswordResetPage(
		@RequestParam(name = TOKEN_KEY) String token,
		PasswordResetForm form,
		ModelAndView modelAndView
	) {
		form.setNewPassword("");
		form.setToken(token);
		return SecurityResources.Views.PASSWORD_RESET_FORM;
	}

	@PostMapping(SecurityResources.Paths.Security.PASSWORD_RESET)
	public ModelAndView sendPasswordReset(
		@Valid PasswordResetForm form,
		BindingResult result,
		RedirectAttributes redirectAttributes
	) {
		if (result.hasErrors()) {
			return new ModelAndView(SecurityResources.Views.PASSWORD_RESET_FORM);
		}

		try {
			ResponseEntity<PasswordResetApiResponseDto> apiResponse = api.passwordReset(
				form.getToken(),
				form.getNewPassword()
			);
			ensure(apiResponse.getStatusCode(), HttpStatus.OK);

			UserMessageHelper.setGlobalSuccessMessage(
				getMessage(MSG_PASSWORD_RESET_SUCCESS),
				redirectAttributes
			);
		} catch (BadRequest exception) {
			UserMessageHelper.setGlobalErrorMessage(
				exception.getResponseBodyAsString(),
				redirectAttributes
			);
			return new ModelAndView(SecurityResources.Views.PASSWORD_RESET_FORM);
		}

		return new ModelAndView(new RedirectView(SecurityResources.Paths.Configuration.LOGIN_PAGE, true));
	}

	private String getMessage(final String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}
	
	private <T> void ensure(final T value, final T expectedValue) {
		if (value != expectedValue) {
			throw new UnexpectedValueException();
		}
	}

}
