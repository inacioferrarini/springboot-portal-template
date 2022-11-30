package com.inacioferrarini.template.portal.sample.todosamplefeature.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inacioferrarini.template.portal.sample.todosamplefeature.resources.TodoFeatureResources;

@Controller
@RequestMapping(TodoFeatureResources.Paths.ToDo.ROOT)
public class TodoController {
	
//	public static class ToDo {
//
//		public static final String ROOT = "/todo";
//		public static final String INDEX = "/";
//		public static final String SHOW_FORM = "/add";
//		public static final String CREATE_FORM = "/add";
//		public static final String EDIT = "/{id}";
//		public static final String UPDATE = "/update/{id}";
//		public static final String DELETE = "/delete/{id}";
//
//	}

	
	@GetMapping
	public String index() {
		
		
		
		return TodoFeatureResources.Views.TODO_LIST;
	}

}
