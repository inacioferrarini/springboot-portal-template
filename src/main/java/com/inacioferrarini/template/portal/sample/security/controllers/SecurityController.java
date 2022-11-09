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
import com.inacioferrarini.template.portal.sample.security.api.requests.ForgotUsernameApiRequestDTO;
import com.inacioferrarini.template.portal.sample.security.api.responses.ActivateUserAccountApiResponseDTO;
import com.inacioferrarini.template.portal.sample.security.api.responses.ForgotUsernameApiResponseDTO;
import com.inacioferrarini.template.portal.sample.security.controllers.forms.ForgotUsernameRequestDTO;
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
			.createActivateAccountApiRequestEntity(activateAccountRequestDTO);
		try {
			ResponseEntity<ActivateUserAccountApiResponseDTO> response = restTemplate.postForEntity(
				SecurityResources.Paths.Api.ACTIVATE_USER,
				requestEntity,
				ActivateUserAccountApiResponseDTO.class
			);

			System.out.println("Received status: " + response.getBody().getStatus());
		} catch (BadRequest exception) {
			// TODO: Convert Error to DTO
			System.out.println("@@" + exception.getResponseBodyAsString() + "@@");
			// TODO: return error page redirection
		}
		
		//
		//
		// TODO: Redirect to LoginForm with a flash message having "Activation Success".
		//
		//
		
		return SecurityResources.Views.ACTIVATE_ACCOUNT_SUCCESS;
	}

	@GetMapping(SecurityResources.Paths.Security.FORGOT_USERNAME)
	public String showForgotUsernamePage(ForgotUsernameRequestDTO forgotUsernameRequestDTO) {
		forgotUsernameRequestDTO.setEmail("");
		return SecurityResources.Views.FORGOT_USERNAME_FORM;
	}

	@PostMapping(SecurityResources.Paths.Security.FORGOT_USERNAME)
	public String sendForgotUsername(
		@Valid ForgotUsernameRequestDTO forgotUsernameRequestDTO,
		BindingResult result
	) {
		if (result.hasErrors()) {
			return SecurityResources.Views.FORGOT_USERNAME_FORM;
		}

		ForgotUsernameApiRequestDTO forgotUsernameApiRequestDTO = new ForgotUsernameApiRequestDTO(
			forgotUsernameRequestDTO.getEmail()
		);
		HttpEntity<ForgotUsernameApiRequestDTO> requestEntity = apiRequestFactory
			.createForgotUsernameApiRequestEntity(forgotUsernameApiRequestDTO);
		try {
			ResponseEntity<ForgotUsernameApiResponseDTO> response = restTemplate.postForEntity(
				SecurityResources.Paths.Api.FORGOT_USERNAME,
				requestEntity,
				ForgotUsernameApiResponseDTO.class
			);

			System.out.println("Received status: " + response.getBody().getStatus());
			System.out.println("Received message: " + response.getBody().getMessage());

		} catch (BadRequest exception) {
			System.out.println("Exception: @@" + exception.getResponseBodyAsString() + "@@");
			// Error? Success? Redirect to Error
		}

		return SecurityResources.Views.FORGOT_USERNAME_FORM; // Success? Redirect to Login
	}

}
