package com.inacioferrarini.template.portal.sample.security.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inacioferrarini.template.portal.sample.security.enums.ActivateUserAccountStatusType;

public class ActivateUserAccountResponseDTO {

	private final ActivateUserAccountStatusType status;

	public ActivateUserAccountResponseDTO(
		@JsonProperty("status") ActivateUserAccountStatusType status
	) {
		this.status = status;
	}

	public ActivateUserAccountStatusType getStatus() {
		return status;
	}

}
