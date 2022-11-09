package com.inacioferrarini.template.portal.sample.security.controllers;

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

import com.inacioferrarini.template.portal.sample.security.api.factories.ApiRequestFactory;
import com.inacioferrarini.template.portal.sample.security.api.requests.ActivateUserAccountApiRequestDTO;
import com.inacioferrarini.template.portal.sample.security.api.requests.ForgotPasswordApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.requests.ForgotUsernameApiRequestDTO;
import com.inacioferrarini.template.portal.sample.security.api.requests.PasswordResetApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.ActivateUserAccountApiResponseDTO;
import com.inacioferrarini.template.portal.sample.security.api.responses.ForgotPasswordApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.ForgotUsernameApiResponseDTO;
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
	public String showLoginPage() {
		System.out.println("@@DEBUG: showLoginPage()@@");
		return SecurityResources.Views.LOGIN_FORM;
	}

	@GetMapping(SecurityResources.Paths.Security.ACTIVATE_ACCOUNT)
	public String activateAccount(
		@RequestParam(name = TOKEN_KEY) String token,
		ModelAndView modelAndView
	) {
		ActivateUserAccountApiRequestDTO activateAccountRequestDTO = new ActivateUserAccountApiRequestDTO(
			token
		);
		HttpEntity<ActivateUserAccountApiRequestDTO> requestEntity = apiRequestFactory
			.requestEntity(activateAccountRequestDTO);
		try {
			ResponseEntity<ActivateUserAccountApiResponseDTO> response = restTemplate.postForEntity(
				SecurityResources.Paths.Api.ACTIVATE_USER,
				requestEntity,
				ActivateUserAccountApiResponseDTO.class
			);

			// TODO: Set flash message success

			System.out.println("Received status: " + response.getBody().getStatus());
		} catch (BadRequest exception) {
			// TODO: Convert Error to DTO
			System.out.println("@@" + exception.getResponseBodyAsString() + "@@");
			// TODO: return error page redirection
		}

		// TODO: Redirect to login
		return SecurityResources.Views.LOGIN_FORM;
	}

	@GetMapping(SecurityResources.Paths.Security.FORGOT_USERNAME)
	public String showForgotUsernamePage(
		ForgotUsernameForm form
	) {
		form.setEmail("");
		return SecurityResources.Views.FORGOT_USERNAME_FORM;
	}

	@PostMapping(SecurityResources.Paths.Security.FORGOT_USERNAME)
	public String sendForgotUsername(
		@Valid ForgotUsernameForm form,
		BindingResult result
	) {
		if (result.hasErrors()) {
			return SecurityResources.Views.FORGOT_USERNAME_FORM;
		}

		ForgotUsernameApiRequestDTO forgotUsernameApiRequestDTO = new ForgotUsernameApiRequestDTO(
			form.getEmail()
		);
		HttpEntity<ForgotUsernameApiRequestDTO> requestEntity = apiRequestFactory
			.requestEntity(forgotUsernameApiRequestDTO);
		try {
			ResponseEntity<ForgotUsernameApiResponseDTO> response = restTemplate.postForEntity(
				SecurityResources.Paths.Api.FORGOT_USERNAME,
				requestEntity,
				ForgotUsernameApiResponseDTO.class
			);

			// TODO: Set flash message success

			System.out.println("Received status: " + response.getBody().getStatus());
			System.out.println("Received message: " + response.getBody().getMessage());

		} catch (BadRequest exception) {
			System.out.println("Exception: @@" + exception.getResponseBodyAsString() + "@@");
			// Error? Success? Redirect to Error
			return SecurityResources.Views.FORGOT_USERNAME_FORM;
		}

		// TODO: Redirect to login
		return SecurityResources.Views.FORGOT_USERNAME_FORM;
	}

	@GetMapping(SecurityResources.Paths.Security.FORGOT_PASSWORD)
	public String showForgotPasswordPage(
		ForgotPasswordForm form
	) {
		form.setUsername("");
		return SecurityResources.Views.FORGOT_PASSWORD_FORM;
	}

	@PostMapping(SecurityResources.Paths.Security.FORGOT_PASSWORD)
	public String sendForgotPassword(
		@Valid ForgotPasswordForm form,
		BindingResult result
	) {
		if (result.hasErrors()) {
			return SecurityResources.Views.FORGOT_PASSWORD_FORM;
		}

		ForgotPasswordApiRequestDto apiRequest = new ForgotPasswordApiRequestDto(
			form.getUsername()
		);
		HttpEntity<ForgotPasswordApiRequestDto> requestEntity = apiRequestFactory
			.requestEntity(apiRequest);
		try {
			ResponseEntity<ForgotPasswordApiResponseDto> response = restTemplate.postForEntity(
				SecurityResources.Paths.Api.FORGOT_PASSWORD,
				requestEntity,
				ForgotPasswordApiResponseDto.class
			);

			// TODO: Set flash message success

			System.out.println("Received status: " + response.getBody().getStatus());
			System.out.println("Received message: " + response.getBody().getMessage());

		} catch (BadRequest exception) {
			System.out.println("Exception: @@" + exception.getResponseBodyAsString() + "@@");
			// Error? Success? Redirect to Error
			return SecurityResources.Views.FORGOT_PASSWORD_FORM;
		}

		// TODO: Redirect to login
		return SecurityResources.Views.FORGOT_PASSWORD_FORM;
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
	public String sendPasswordReset(
		@Valid PasswordResetForm form,
		BindingResult result
	) {
		if (result.hasErrors()) {
			return SecurityResources.Views.PASSWORD_RESET_FORM;
		}

		PasswordResetApiRequestDto apiRequest = new PasswordResetApiRequestDto(
			form.getToken(),
			form.getNewPassword()
		);
		HttpEntity<PasswordResetApiRequestDto> requestEntity = apiRequestFactory
			.requestEntity(apiRequest);
		try {
			ResponseEntity<PasswordResetApiResponseDto> response = restTemplate.postForEntity(
				SecurityResources.Paths.Api.PASSWORD_RESET,
				requestEntity,
				PasswordResetApiResponseDto.class
			);

			// TODO: Set flash message success

			System.out.println("Received status: " + response.getBody().getStatus());

		} catch (BadRequest exception) {
			System.out.println("Exception: @@" + exception.getResponseBodyAsString() + "@@");
			// Error? Success? Redirect to Error
			return SecurityResources.Views.PASSWORD_RESET_FORM;
		}

		// TODO: Redirect to login
		return SecurityResources.Views.PASSWORD_RESET_FORM;
	}

}
