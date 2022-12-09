package com.inacioferrarini.template.portal.sample.core.messages;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.support.SessionFlashMapManager;

public class UserMessageHelper {

	public static Optional<UserMessageDto> getGlobalMessage(
		final HttpServletRequest request
	) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

		if (inputFlashMap == null) {
			return Optional.empty();
		}

		return Optional.of(
			(UserMessageDto) inputFlashMap.get(MessageConstants.USER_MESSAGE_KEY)
		);
	}

	public static void setGlobalMessage(
		final UserMessageDto globalMessage,
		final RedirectAttributes redirectAttributes
	) {
		redirectAttributes.addFlashAttribute(
			MessageConstants.USER_MESSAGE_KEY,
			globalMessage
		);
	}

	public static void setGlobalSuccessMessage(
		final String message,
		final RedirectAttributes redirectAttributes
	) {
		final UserMessageDto userMessage = new UserMessageDto(
			message,
			UserMessageDto.Type.SUCCESS
		);
		setGlobalMessage(userMessage, redirectAttributes);
	}

	public static void setGlobalErrorMessage(
		final String message,
		final RedirectAttributes redirectAttributes
	) {
		final UserMessageDto userMessage = new UserMessageDto(
			message,
			UserMessageDto.Type.ERROR
		);
		setGlobalMessage(userMessage, redirectAttributes);
	}

	public static void setGlobalMessage(
		final UserMessageDto globalMessage,
		final HttpServletRequest request,
		final HttpServletResponse response
	) {
		final FlashMap flashMap = new FlashMap();
		final FlashMapManager flashMapManager = new SessionFlashMapManager();

		flashMap.put(
			MessageConstants.USER_MESSAGE_KEY,
			globalMessage
		);

		flashMapManager.saveOutputFlashMap(flashMap, request, response);
	}

	public static void setGlobalSuccessMessage(
		final String message,
		final HttpServletRequest request,
		final HttpServletResponse response
	) {
		final UserMessageDto userMessage = new UserMessageDto(
			message,
			UserMessageDto.Type.SUCCESS
		);
		setGlobalMessage(userMessage, request, response);
	}

	public static void setGlobalErrorMessage(
		final String message,
		final HttpServletRequest request,
		final HttpServletResponse response
	) {
		final UserMessageDto userMessage = new UserMessageDto(
			message,
			UserMessageDto.Type.ERROR
		);
		setGlobalMessage(userMessage, request, response);
	}

	public static void setLocalMessage(
		final UserMessageDto globalMessage,
		final Model model
	) {
		model.addAttribute(
			MessageConstants.USER_MESSAGE_KEY,
			globalMessage
		);
	}
		
	public static void setLocalSuccessMessage(
		final String message,
		final Model model
	) {
		final UserMessageDto userMessage = new UserMessageDto(
			message,
			UserMessageDto.Type.SUCCESS
		);
		setLocalMessage(userMessage, model);
	}

	public static void setLocalErrorMessage(
		final String message,
		final Model model
	) {
		final UserMessageDto userMessage = new UserMessageDto(
			message,
			UserMessageDto.Type.ERROR
		);
		setLocalMessage(userMessage, model);
	}

}
