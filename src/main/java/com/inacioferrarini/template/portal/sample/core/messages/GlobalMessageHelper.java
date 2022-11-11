package com.inacioferrarini.template.portal.sample.core.messages;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

public class GlobalMessageHelper {

	public static Optional<GlobalMessageDto> getGlobalMessage(
		final HttpServletRequest request
	) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

		if (inputFlashMap == null) {
			return Optional.empty();
		}

		return Optional.of(
			(GlobalMessageDto) inputFlashMap.get(MessageConstants.GLOBAL_MESSAGE_KEY)
		);
	}

	public static void setGlobalMessage(
		final GlobalMessageDto globalMessage,
		final RedirectAttributes redirectAttributes
	) {
		redirectAttributes.addFlashAttribute(
			MessageConstants.GLOBAL_MESSAGE_KEY,
			globalMessage
		);
	}

	public static void setGlobalSuccessMessage(
		final String message,
		final RedirectAttributes redirectAttributes
	) {
		final GlobalMessageDto globalMessage = new GlobalMessageDto(
			message,
			GlobalMessageDto.Type.SUCCESS
		);
		setGlobalMessage(globalMessage, redirectAttributes);
	}

	public static void setGlobalErrorMessage(
		final String message,
		final RedirectAttributes redirectAttributes
	) {
		final GlobalMessageDto globalMessage = new GlobalMessageDto(
			message,
			GlobalMessageDto.Type.ERROR
		);
		setGlobalMessage(globalMessage, redirectAttributes);
	}

}
