package com.inacioferrarini.template.portal.sample.security.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Conflict;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.inacioferrarini.template.portal.sample.core.messages.GlobalMessageHelper;
import com.inacioferrarini.template.portal.sample.security.api.factories.ApiRequestFactory;
import com.inacioferrarini.template.portal.sample.security.api.requests.RegisterAccountApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.RegisterAccountApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.controllers.forms.RegisterAccountForm;
import com.inacioferrarini.template.portal.sample.security.resources.SecurityResources;

@Controller
@RequestMapping(SecurityResources.Paths.Security.ROOT)
public class RegisterAccountController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiRequestFactory apiRequestFactory;
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@GetMapping(SecurityResources.Paths.Security.REGISTER_ACCOUNT)
	public String showRegisterAccountForm(
		RegisterAccountForm form
	) {
		addSupportedIdioms(form);
		form.setAgreedToTerms(false);
		return SecurityResources.Views.REGISTER_ACCOUNT_FORM;
	}

	@PostMapping(SecurityResources.Paths.Security.REGISTER_ACCOUNT)
	public ModelAndView registerAccount(
		@Valid RegisterAccountForm form,
		BindingResult result,
		RedirectAttributes redirectAttributes,
		HttpServletRequest request
	) {
		if (result.hasErrors()) {
			addSupportedIdioms(form);
			return new ModelAndView(SecurityResources.Views.REGISTER_ACCOUNT_FORM);
		}
		
		RegisterAccountApiRequestDto createAccountApiRequest = new RegisterAccountApiRequestDto(
			form.getIdiom(),
			form.getUsername(),
			form.getEmail(),
			form.getPassword()
		);
		HttpEntity<RegisterAccountApiRequestDto> apiRequest = apiRequestFactory
			.requestEntity(createAccountApiRequest);
		try {
			ResponseEntity<RegisterAccountApiResponseDto> apiResponse = restTemplate.postForEntity(
				SecurityResources.Paths.Api.REGISTER_ACCOUNT,
				apiRequest,
				RegisterAccountApiResponseDto.class
			);

			GlobalMessageHelper.setGlobalSuccessMessage(
				"Register Account - activation email sent",
				redirectAttributes
			);
			
System.out.println("Received status: " + apiResponse.getBody().getStatus());
System.out.println("Received message: " + apiResponse.getBody().getMessage());
		} catch (BadRequest | Conflict exception) {
			GlobalMessageHelper.setGlobalErrorMessage(
				exception.getResponseBodyAsString(),
				redirectAttributes
			);
			
			return new ModelAndView(new RedirectView(SecurityResources.Paths.Configuration.REGISTER_ACCOUNT, true));
		}

		return new ModelAndView(new RedirectView(SecurityResources.Paths.Configuration.LOGIN_PAGE, true));
	}
	
	@PostMapping(SecurityResources.Paths.Security.CHANGE_IDIOM)
	public String changeIdiom(
		RegisterAccountForm form,
		HttpServletRequest request,
		HttpServletResponse response
	) {
		final Locale selectedIdiom = Locale.forLanguageTag(form.getIdiom());		
		addSupportedIdioms(form);
		localeResolver.setLocale(request, response, selectedIdiom);
		return SecurityResources.Views.REGISTER_ACCOUNT_FORM;
	}
	
	private void addSupportedIdioms(RegisterAccountForm form) {
		final List<Locale> idioms = Arrays.asList(
			Locale.forLanguageTag("en-US"),
			Locale.forLanguageTag("pt-BR")
		);
		form.setSupportedIdiomList(idioms);		
	}

}
