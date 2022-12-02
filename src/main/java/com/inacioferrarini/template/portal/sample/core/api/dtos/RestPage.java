package com.inacioferrarini.template.portal.sample.core.api.dtos;

import java.util.List;

public class RestPage<T> {

	private final List<T> content;
	// pageable
	private final int totalPages;
	private final int totalElements;
	private final boolean last;
	private final int size;
	private final int number;
	// sort
	private final int numberOfElements;
	private final boolean first;
	private final boolean empty;

	public RestPage(
		final List<T> content,
		final int totalPages,
		final int totalElements,
		final boolean last,
		final int size,
		final int number,
		final int numberOfElements,
		final boolean first,
		final boolean empty
	) {
		this.content = content;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.last = last;
		this.size = size;
		this.number = number;
		this.numberOfElements = numberOfElements;
		this.first = first;
		this.empty = empty;
	}

	public List<T> getContent() {
		return content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public boolean isLast() {
		return last;
	}

	public int getSize() {
		return size;
	}

	public int getNumber() {
		return number;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public boolean isFirst() {
		return first;
	}

	public boolean isEmpty() {
		return empty;
	}

}
