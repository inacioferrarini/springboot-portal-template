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

	public static void setLocalMessage(
		final GlobalMessageDto globalMessage,
		final Model model
	) {
		model.addAttribute(
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

	public static void setGlobalMessage(
		final GlobalMessageDto globalMessage,
		final HttpServletRequest request,
		final HttpServletResponse response
	) {
		final FlashMap flashMap = new FlashMap();
		final FlashMapManager flashMapManager = new SessionFlashMapManager();

		flashMap.put(
			MessageConstants.GLOBAL_MESSAGE_KEY,
			globalMessage
		);

		flashMapManager.saveOutputFlashMap(flashMap, request, response);
	}

	public static void setGlobalSuccessMessage(
		final String message,
		final HttpServletRequest request,
		final HttpServletResponse response
	) {
		final GlobalMessageDto globalMessage = new GlobalMessageDto(
			message,
			GlobalMessageDto.Type.SUCCESS
		);
		setGlobalMessage(globalMessage, request, response);
	}

	public static void setGlobalErrorMessage(
		final String message,
		final HttpServletRequest request,
		final HttpServletResponse response
	) {
		final GlobalMessageDto globalMessage = new GlobalMessageDto(
			message,
			GlobalMessageDto.Type.ERROR
		);
		setGlobalMessage(globalMessage, request, response);
	}

	public static void setLocalSuccessMessage(
		final String message,
		final Model model
	) {
		final GlobalMessageDto globalMessage = new GlobalMessageDto(
			message,
			GlobalMessageDto.Type.SUCCESS
		);
		setLocalMessage(globalMessage, model);
	}

	public static void setLocalErrorMessage(
		final String message,
		final Model model
	) {
		final GlobalMessageDto globalMessage = new GlobalMessageDto(
			message,
			GlobalMessageDto.Type.ERROR
		);
		setLocalMessage(globalMessage, model);
	}

}
