package com.inacioferrarini.template.portal.sample.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.inacioferrarini.template.portal.sample.security.api.factories.ApiRequestFactory;
import com.inacioferrarini.template.portal.sample.security.api.requests.ActivateUserAccountRequestDTO;
import com.inacioferrarini.template.portal.sample.security.api.responses.ActivateUserAccountResponseDTO;
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
		return SecurityResources.Views.LOGIN;
	}

	@GetMapping(SecurityResources.Paths.Security.ACTIVATE_ACCOUNT)
	public String activateAccount(
		@RequestParam(name = TOKEN_KEY) String token,
		ModelAndView modelAndView
	) {
		ActivateUserAccountRequestDTO activateAccountRequestDTO = new ActivateUserAccountRequestDTO(
			token
		);
		HttpEntity<ActivateUserAccountRequestDTO> requestEntity = apiRequestFactory
			.createActivateAccountRequestEntity(activateAccountRequestDTO);
		try {
			ResponseEntity<ActivateUserAccountResponseDTO> response = restTemplate.postForEntity(
				SecurityResources.Paths.Api.ACTIVATE_USER,
				requestEntity,
				ActivateUserAccountResponseDTO.class
			);

			System.out.println("Received status: " + response.getBody().getStatus());
		} catch (BadRequest exception) {
			// TODO: Convert Error to DTO
			System.out.println("@@" + exception.getResponseBodyAsString() + "@@");
			// TODO: return error page redirection
		}

		return SecurityResources.Views.ACTIVATE_ACCOUNT_SUCCESS;
	}

}
