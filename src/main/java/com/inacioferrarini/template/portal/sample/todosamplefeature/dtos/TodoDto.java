package com.inacioferrarini.template.portal.sample.todosamplefeature.dtos;

import java.time.LocalDate;

public class TodoDto {

	public enum StatusType {
		OPEN, COMPLETED;
	}

	private final Long id;
	private final String name;
	private final String description;
	private final LocalDate creationDate; // TODO: Json value conversion is failing
	private final StatusType status;

	public TodoDto(
		final Long id,
		final String name,
		final String description,
		final LocalDate creationDate,
		final StatusType status
	) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.status = status;
	}

	public Long getId() {
		return id;
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
