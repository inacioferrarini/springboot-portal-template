package com.inacioferrarini.template.portal.sample.todosamplefeature.dtos;

import java.time.LocalDate;

public class TodoDto {

	public enum StatusType {
		OPEN, COMPLETED;
	}

	private final String name;
	private final String description;
	private final LocalDate creationDate;
	private final StatusType status;

	public TodoDto(
		final String name,
		final String description,
		final LocalDate creationDate,
		final StatusType status
	) {
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public StatusType getStatus() {
		return status;
	}

}
