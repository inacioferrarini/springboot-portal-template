package com.inacioferrarini.template.portal.sample.security.api.factories;

import org.springframework.http.HttpEntity;

import com.inacioferrarini.template.portal.sample.security.api.requests.ActivateUserAccountApiRequestDTO;
import com.inacioferrarini.template.portal.sample.security.api.requests.AuthenticateApiRequestDTO;
import com.inacioferrarini.template.portal.sample.security.api.requests.ForgotPasswordApiRequestDto;
import com.inacioferrarini.template.portal.sample.security.api.requests.ForgotUsernameApiRequestDTO;

public interface ApiRequestFactory {

	HttpEntity<AuthenticateApiRequestDTO>
		createAuthenticationApiRequestEntity(AuthenticateApiRequestDTO authenticateApiRequestDTO);

	HttpEntity<ActivateUserAccountApiRequestDTO>
		createActivateAccountApiRequestEntity(ActivateUserAccountApiRequestDTO activateAccountApiRequestDTO);

	HttpEntity<ForgotUsernameApiRequestDTO>
		createForgotUsernameApiRequestEntity(ForgotUsernameApiRequestDTO forgotUsernameApiRequestDTO);

	HttpEntity<ForgotPasswordApiRequestDto>
		createForgotPasswordApiRequestEntity(ForgotPasswordApiRequestDto forgotPasswordApiRequestDto);

}
