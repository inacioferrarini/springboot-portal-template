package com.inacioferrarini.template.portal.sample.security.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import com.inacioferrarini.template.portal.sample.security.api.factories.ApiRequestFactory;
import com.inacioferrarini.template.portal.sample.security.api.requests.ActivateUserAccountApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.requests.ForgotPasswordApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.requests.ForgotUsernameApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.requests.PasswordResetApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.ActivateUserAccountApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.ForgotPasswordApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.ForgotUsernameApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.PasswordResetApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.controllers.forms.ForgotPasswordForm;
import com.inacioferrarini.template.portal.sample.security.controllers.forms.ForgotUsernameForm;
import com.inacioferrarini.template.portal.sample.security.controllers.forms.PasswordResetForm;
import com.inacioferrarini.template.portal.sample.security.resources.SecurityResources;

@Controller
@RequestMapping(SecurityResources.Paths.Security.ROOT)
public class SecurityController {

	// TODO: Create Constants for properties
	private static final String TOKEN_KEY = "token";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiRequestFactory apiRequestFactory;

	@GetMapping(SecurityResources.Paths.Security.LOGIN)
	public String showLoginPage(
		HttpServletRequest request
	) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			String successMessage = (String) inputFlashMap.get("successMessage");
			System.out.println("@@ successMessage=" + successMessage);
		}
		
		System.out.println("@@DEBUG: showLoginPage()@@");
		
		return SecurityResources.Views.LOGIN_FORM;
	}

	@GetMapping(SecurityResources.Paths.Security.ACTIVATE_ACCOUNT)
	public ModelAndView activateAccount(
		@RequestParam(name = TOKEN_KEY) String token,
		RedirectAttributes redirectAttributes
	) {
		ActivateUserAccountApiRequestDto activateAccountRequest = new ActivateUserAccountApiRequestDto(
			token
		);
		HttpEntity<ActivateUserAccountApiRequestDto> apiRequest = apiRequestFactory
			.requestEntity(activateAccountRequest);
		try {
			ResponseEntity<ActivateUserAccountApiResponseDto> apiResponse = restTemplate.postForEntity(
				SecurityResources.Paths.Api.ACTIVATE_USER,
				apiRequest,
				ActivateUserAccountApiResponseDto.class
			);

// TODO: Set flash message success
redirectAttributes.addFlashAttribute(
	"successMessage",
	"Activate Account - Success"
);			

			System.out.println("Received status: " + apiResponse.getBody().getStatus());
		} catch (BadRequest exception) {
			System.out.println("@@" + exception.getResponseBodyAsString() + "@@");
			// Error? Set a Proper Message using flashAttribute
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

		ForgotUsernameApiRequestDto forgotUsernameApiRequest = new ForgotUsernameApiRequestDto(
			form.getEmail()
		);
		HttpEntity<ForgotUsernameApiRequestDto> apiRequest = apiRequestFactory
			.requestEntity(forgotUsernameApiRequest);
		try {
			ResponseEntity<ForgotUsernameApiResponseDto> apiResponse = restTemplate.postForEntity(
				SecurityResources.Paths.Api.FORGOT_USERNAME,
				apiRequest,
				ForgotUsernameApiResponseDto.class
			);

// TODO: Set flash message success
redirectAttributes.addFlashAttribute(
	"successMessage",
	"Forgot username - email sent"
);

			System.out.println("Received status: " + apiResponse.getBody().getStatus());
			System.out.println("Received message: " + apiResponse.getBody().getMessage());

		} catch (BadRequest exception) {
			System.out.println("Exception: @@" + exception.getResponseBodyAsString() + "@@");
			// Error? Set a Proper Message using flashAttribute
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

		ForgotPasswordApiRequestDto forgotPasswordApiRequest = new ForgotPasswordApiRequestDto(
			form.getUsername()
		);
		HttpEntity<ForgotPasswordApiRequestDto> apiRequest = apiRequestFactory
			.requestEntity(forgotPasswordApiRequest);
		try {
			ResponseEntity<ForgotPasswordApiResponseDto> apiResponse = restTemplate.postForEntity(
				SecurityResources.Paths.Api.FORGOT_PASSWORD,
				apiRequest,
				ForgotPasswordApiResponseDto.class
			);

// TODO: Set flash message success
redirectAttributes.addFlashAttribute(
	"successMessage",
	"Forgot password - email sent"
);

			System.out.println("Received status: " + apiResponse.getBody().getStatus());
			System.out.println("Received message: " + apiResponse.getBody().getMessage());

		} catch (BadRequest exception) {
			System.out.println("Exception: @@" + exception.getResponseBodyAsString() + "@@");
			// Error? Set a Proper Message using flashAttribute
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

		PasswordResetApiRequestDto passwordRestApiRequest = new PasswordResetApiRequestDto(
			form.getToken(),
			form.getNewPassword()
		);
		HttpEntity<PasswordResetApiRequestDto> apiRequest = apiRequestFactory
			.requestEntity(passwordRestApiRequest);
		try {
			ResponseEntity<PasswordResetApiResponseDto> apiResponse = restTemplate.postForEntity(
				SecurityResources.Paths.Api.PASSWORD_RESET,
				apiRequest,
				PasswordResetApiResponseDto.class
			);

// TODO: Set flash message success
redirectAttributes.addFlashAttribute(
	"successMessage",
	"Password Reset Success"
);

			System.out.println("Received status: " + apiResponse.getBody().getStatus());

		} catch (BadRequest exception) {
			System.out.println("Exception: @@" + exception.getResponseBodyAsString() + "@@");
			// Error? Set a Proper Message using flashAttribute
			return new ModelAndView(SecurityResources.Views.PASSWORD_RESET_FORM);
		}

		return new ModelAndView(new RedirectView(SecurityResources.Paths.Configuration.LOGIN_PAGE, true));
	}

}
