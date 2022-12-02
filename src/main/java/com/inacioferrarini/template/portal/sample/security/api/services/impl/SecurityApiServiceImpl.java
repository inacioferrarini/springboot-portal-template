package com.inacioferrarini.template.portal.sample.security.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inacioferrarini.template.portal.sample.core.api.factories.ApiRequestFactory;
import com.inacioferrarini.template.portal.sample.security.api.requests.ActivateUserAccountApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.requests.ForgotPasswordApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.requests.ForgotUsernameApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.requests.PasswordResetApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.requests.RegisterAccountApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.ActivateUserAccountApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.ForgotPasswordApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.ForgotUsernameApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.PasswordResetApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.RegisterAccountApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.services.SecurityApiService;
import com.inacioferrarini.template.portal.sample.security.resources.SecurityResources;

@Service
public class SecurityApiServiceImpl implements SecurityApiService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiRequestFactory apiRequestFactory;

	@Override
	public ResponseEntity<RegisterAccountApiResponseDto> registerAccount(
		String idiom,
		String username,
		String email,
		String password
	) {
		RegisterAccountApiRequestDto createAccountApiRequest = new RegisterAccountApiRequestDto(
			idiom,
			username,
			email,
			password
		);
		HttpEntity<RegisterAccountApiRequestDto> apiRequest = apiRequestFactory
			.requestEntity(createAccountApiRequest);
		try {
			return restTemplate.postForEntity(
				SecurityResources.Paths.Api.REGISTER_ACCOUNT,
				apiRequest,
				RegisterAccountApiResponseDto.class
			);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ResponseEntity<ActivateUserAccountApiResponseDto> activateAccount(
		String token
	) {
		ActivateUserAccountApiRequestDto activateAccountRequest = new ActivateUserAccountApiRequestDto(
			token
		);
		HttpEntity<ActivateUserAccountApiRequestDto> apiRequest = apiRequestFactory
			.requestEntity(activateAccountRequest);
		try {
			return restTemplate.postForEntity(
				SecurityResources.Paths.Api.ACTIVATE_USER,
				apiRequest,
				ActivateUserAccountApiResponseDto.class
			);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ResponseEntity<ForgotUsernameApiResponseDto> forgotUsername(
		String email
	) {
		ForgotUsernameApiRequestDto forgotUsernameApiRequest = new ForgotUsernameApiRequestDto(email);
		HttpEntity<ForgotUsernameApiRequestDto> apiRequest = apiRequestFactory
			.requestEntity(forgotUsernameApiRequest);
		try {
			return restTemplate.postForEntity(
				SecurityResources.Paths.Api.FORGOT_USERNAME,
				apiRequest,
				ForgotUsernameApiResponseDto.class
			);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ResponseEntity<ForgotPasswordApiResponseDto> forgotPassword(
		String username
	) {
		ForgotPasswordApiRequestDto forgotPasswordApiRequest = new ForgotPasswordApiRequestDto(username);
		HttpEntity<ForgotPasswordApiRequestDto> apiRequest = apiRequestFactory
			.requestEntity(forgotPasswordApiRequest);
		try {
			return restTemplate.postForEntity(
				SecurityResources.Paths.Api.FORGOT_PASSWORD,
				apiRequest,
				ForgotPasswordApiResponseDto.class
			);
		} catch (Exception ex) {
			throw ex;
		}

	}

	@Override
	public ResponseEntity<PasswordResetApiResponseDto> passwordReset(
		String token,
		String newPassword
	) {
		PasswordResetApiRequestDto passwordRestApiRequest = new PasswordResetApiRequestDto(
			token,
			newPassword
		);
		HttpEntity<PasswordResetApiRequestDto> apiRequest = apiRequestFactory
			.requestEntity(passwordRestApiRequest);
		try {
			return restTemplate.postForEntity(
				SecurityResources.Paths.Api.PASSWORD_RESET,
				apiRequest,
				PasswordResetApiResponseDto.class
			);
		} catch (Exception ex) {
			throw ex;
		}
	}

}
