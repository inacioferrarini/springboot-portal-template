package com.inacioferrarini.template.portal.sample.security.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inacioferrarini.template.portal.sample.security.api.enums.ActivateUserAccountStatusType;

public class ActivateUserAccountApiResponseDTO {

	private final ActivateUserAccountStatusType status;

	public ActivateUserAccountApiResponseDTO(
		@JsonProperty("status") ActivateUserAccountStatusType status
	) {
		this.status = status;
	}

	public ActivateUserAccountStatusType getStatus() {
		return status;
	}

}
