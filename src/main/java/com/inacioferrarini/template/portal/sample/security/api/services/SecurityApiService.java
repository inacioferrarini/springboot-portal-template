package com.inacioferrarini.template.portal.sample.security.api.services;

import org.springframework.http.ResponseEntity;

import com.inacioferrarini.template.portal.sample.security.api.responses.ActivateUserAccountApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.ForgotPasswordApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.ForgotUsernameApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.PasswordResetApiResponseDto;
import com.inacioferrarini.template.portal.sample.security.api.responses.RegisterAccountApiResponseDto;

public interface SecurityApiService {

	ResponseEntity<RegisterAccountApiResponseDto> registerAccount(
		String idiom,
		String username,
		String email,
		String password
	);

	ResponseEntity<ActivateUserAccountApiResponseDto> activateAccount(
		String token
	);

	ResponseEntity<ForgotUsernameApiResponseDto> forgotUsername(
		String email
	);

	ResponseEntity<ForgotPasswordApiResponseDto> forgotPassword(
		String username
	);

	ResponseEntity<PasswordResetApiResponseDto> passwordReset(
		String token,
		String newPassword
	);

}
