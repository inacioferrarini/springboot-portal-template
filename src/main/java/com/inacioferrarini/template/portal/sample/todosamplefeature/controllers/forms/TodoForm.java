package com.inacioferrarini.template.portal.sample.todosamplefeature.controllers.forms;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.inacioferrarini.template.portal.sample.todosamplefeature.dtos.TodoDto.StatusType;

public class TodoForm {

	private List<StatusType> statusList;

	@NotEmpty
	@Length(min = 8, max = 30)
	private String name;

	@NotEmpty
	@Length(min = 8, max = 200)
	private String description;

	@NotNull
	private StatusType status;

	public TodoForm() {
	}

	public List<StatusType> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<StatusType> statusList) {
		this.statusList = statusList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

}
