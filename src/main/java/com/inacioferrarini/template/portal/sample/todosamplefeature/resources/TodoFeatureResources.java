package com.inacioferrarini.template.portal.sample.todosamplefeature.resources;

public class TodoFeatureResources {

	public static class Paths {

		public static class Api {
		}

		public static class ToDo {

			public static final String ROOT = "/todo";
			public static final String INDEX = "/";
			public static final String SHOW_FORM = "/add";
			public static final String CREATE_FORM = "/add";
			public static final String EDIT = "/{id}";
			public static final String UPDATE = "/update/{id}";
			public static final String DELETE = "/delete/{id}";

		}

	}

	public static class Views {

		public static final String TODO_FORM = "todo-sample-feature/todo-form";
		public static final String TODO_LIST = "todo-sample-feature/todo-list";

	}

}
