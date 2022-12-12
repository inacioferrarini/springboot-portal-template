package com.inacioferrarini.template.portal.sample.todosamplefeature.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.inacioferrarini.template.portal.sample.core.api.dtos.RestPage;
import com.inacioferrarini.template.portal.sample.core.messages.UserMessageHelper;
import com.inacioferrarini.template.portal.sample.security.components.AuthenticationService;
import com.inacioferrarini.template.portal.sample.todosamplefeature.apis.services.TodoApiService;
import com.inacioferrarini.template.portal.sample.todosamplefeature.controllers.forms.TodoForm;
import com.inacioferrarini.template.portal.sample.todosamplefeature.dtos.TodoDto;
import com.inacioferrarini.template.portal.sample.todosamplefeature.dtos.TodoDto.StatusType;
import com.inacioferrarini.template.portal.sample.todosamplefeature.resources.TodoFeatureResources;

@Controller
@RequestMapping(TodoFeatureResources.Paths.ToDo.ROOT)
public class TodoController {

	private static final String FORM = "todo-sample-feature/todo-form";
	private static final String LIST = "todo-sample-feature/todo-list";

	// Message constants

	static final String API_CREATE_ERROR = "message.api.create.error.message";
	static final String API_CREATE_SUCCESS = "message.api.create.success.message";
	static final String API_DELETE_ERROR = "message.api.delete.error.message";
	static final String API_DELETE_SUCCESS = "message.api.delete.success.message";
	static final String API_DELETE_MANY_ERROR = "message.api.deleteMany.error.message";
	static final String API_DELETE_MANY_SUCCESS = "message.api.deleteMany.success.message";
	static final String API_FIND_ERROR = "message.api.find.error.message";
	static final String API_UPDATE_ERROR = "message.api.Update.error.message";
	static final String API_UPDATE_SUCCESS = "message.api.update.success.message";

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private TodoApiService api;

	@GetMapping
	public String index(
		@RequestParam(required = false, defaultValue = "") String name,
		@RequestParam(required = false, defaultValue = "") String description,
		@RequestParam(required = false, defaultValue = "1") int page,
		@RequestParam(required = false, defaultValue = "10") int size,
		Model model,
		RedirectAttributes redirectAttributes
	) {
		try {
			ResponseEntity<RestPage<TodoDto>> apiResponse = api.findAll(
				authenticationService.getUser().getToken(),
				name,
				description,
				page,
				size
			);

			model.addAttribute("name", name);
			model.addAttribute("description", description);
			model.addAttribute("page", apiResponse.getBody().getNumber() + 1);
			model.addAttribute("size", size);
			model.addAttribute("todoList", apiResponse.getBody());
		} catch (Exception ex) {
			UserMessageHelper.setGlobalErrorMessage(
				getMessage(API_FIND_ERROR),
				redirectAttributes
			);
		}

		return LIST;
	}

	@GetMapping("/add")
	public String add(
		TodoForm form,
		Model model
	) {
		model.addAttribute("action", TodoFeatureResources.Paths.ToDo.ROOT + "/add");
		populateDropDowns(form);
		form.setName("");
		form.setDescription("");
		form.setStatus(StatusType.OPEN);

		return FORM;
	}

	@PostMapping("/add")
	public ModelAndView create(
		@Valid TodoForm form,
		BindingResult result,
		RedirectAttributes redirectAttributes,
		Model model
	) {
		if (result.hasErrors()) {
			model.addAttribute("action", TodoFeatureResources.Paths.ToDo.ROOT + "/add");
			populateDropDowns(form);
			return new ModelAndView(FORM);
		}

		try {
			ResponseEntity<TodoDto> apiResponse = api.create(
				authenticationService.getUser().getToken(),
				form.getName(),
				form.getDescription(),
				form.getStatus()
			);

			UserMessageHelper.setGlobalSuccessMessage(
				getMessage(API_CREATE_SUCCESS),
				redirectAttributes
			);
		} catch (Exception ex) {
			UserMessageHelper.setLocalErrorMessage(getMessage(API_CREATE_ERROR), model);
			model.addAttribute("action", TodoFeatureResources.Paths.ToDo.ROOT + "/add");
			populateDropDowns(form);
			return new ModelAndView(FORM);
		}

		return new ModelAndView(new RedirectView(TodoFeatureResources.Paths.ToDo.ROOT, true));
	}

	@GetMapping("/{id}")
	public String edit(
		@PathVariable Long id,
		TodoForm form,
		RedirectAttributes redirectAttributes,
		Model model
	) {
		try {
			ResponseEntity<TodoDto> apiResponse = api.findById(
				authenticationService.getUser().getToken(),
				id
			);

			model.addAttribute("action", TodoFeatureResources.Paths.ToDo.ROOT + "/update/" + id);
			populateDropDowns(form);
			form.setName(apiResponse.getBody().getName());
			form.setDescription(apiResponse.getBody().getDescription());
			form.setStatus(apiResponse.getBody().getStatus());
		} catch (Exception ex) {
			UserMessageHelper.setGlobalErrorMessage(
				getMessage(API_FIND_ERROR),
				redirectAttributes
			);
			return LIST;
		}

		return FORM;
	}

	@PostMapping("/update/{id}")
	public ModelAndView update(
		@PathVariable Long id,
		@Valid TodoForm form,
		BindingResult result,
		RedirectAttributes redirectAttributes,
		Model model
	) {
		if (result.hasErrors()) {
			model.addAttribute("action", TodoFeatureResources.Paths.ToDo.ROOT + "/update/" + id);
			populateDropDowns(form);
			return new ModelAndView(FORM);
		}

		try {
			ResponseEntity<TodoDto> apiResponse = api.update(
				authenticationService.getUser().getToken(),
				id,
				form.getName(),
				form.getDescription(),
				form.getStatus()
			);

			UserMessageHelper.setGlobalSuccessMessage(
				getMessage(API_UPDATE_SUCCESS),
				redirectAttributes
			);
		} catch (Exception ex) {
			UserMessageHelper.setLocalErrorMessage(getMessage(API_UPDATE_ERROR), model);
			model.addAttribute("action", TodoFeatureResources.Paths.ToDo.ROOT + "/update/" + id);
			populateDropDowns(form);
			return new ModelAndView(FORM);
		}

		return new ModelAndView(new RedirectView(TodoFeatureResources.Paths.ToDo.ROOT, true));
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(
		@PathVariable Long id,
		@RequestParam(required = false, defaultValue = "") String name,
		@RequestParam(required = false, defaultValue = "") String description,
		@RequestParam(required = false, defaultValue = "1") int page,
		@RequestParam(required = false, defaultValue = "10") int size,
		RedirectAttributes redirectAttributes
	) {
		try {
			api.delete(
				authenticationService.getUser().getToken(),
				id
			);

			UserMessageHelper.setGlobalSuccessMessage(
				getMessage(API_DELETE_SUCCESS),
				redirectAttributes
			);
		} catch (Exception ex) {
			UserMessageHelper.setGlobalErrorMessage(
				getMessage(API_DELETE_ERROR),
				redirectAttributes
			);
		}

		String indexUrl = getIndexRedirectUrl(name, description, page, size);
		return new ModelAndView(new RedirectView(indexUrl, true));
	}

	@GetMapping("/delete")
	public ModelAndView delete(
		@RequestParam(required = false) List<Long> idList,
		@RequestParam(required = false, defaultValue = "") String name,
		@RequestParam(required = false, defaultValue = "") String description,
		@RequestParam(required = false, defaultValue = "1") int page,
		@RequestParam(required = false, defaultValue = "10") int size,
		RedirectAttributes redirectAttributes
	) {
		try {
			api.deleteMany(
				authenticationService.getUser().getToken(),
				idList
			);

			UserMessageHelper.setGlobalSuccessMessage(
				getMessage(API_DELETE_MANY_SUCCESS),
				redirectAttributes
			);
		} catch (Exception ex) {
			UserMessageHelper.setGlobalErrorMessage(
				getMessage(API_DELETE_MANY_ERROR),
				redirectAttributes
			);
		}

		String indexUrl = getIndexRedirectUrl(name, description, page, size);
		return new ModelAndView(new RedirectView(indexUrl, true));
	}

	private void populateDropDowns(TodoForm form) {
		form.setStatusList(Arrays.asList(StatusType.values()));
	}

	private String getIndexRedirectUrl(
		final String name,
		final String description,
		final int page,
		final int size
	) {
		return TodoFeatureResources.Paths.ToDo.ROOT
			.concat("?name=").concat(name)
			.concat("&description=").concat(description)
			.concat("&page=").concat("" + page)
			.concat("&size=").concat("" + size);
	}

	private String getMessage(final String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}

}
