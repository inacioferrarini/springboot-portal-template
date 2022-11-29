package com.inacioferrarini.template.portal.sample.todosamplefeature.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inacioferrarini.template.portal.sample.todosamplefeature.resources.TodoFeatureResources;

@Controller
@RequestMapping(TodoFeatureResources.Paths.ToDo.ROOT)
public class TodoController {

	@GetMapping(TodoFeatureResources.Paths.ToDo.INDEX)
	public String index() {
		return TodoFeatureResources.Views.TODO_LIST;
	}

}
