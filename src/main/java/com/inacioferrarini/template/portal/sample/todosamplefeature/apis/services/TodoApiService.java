package com.inacioferrarini.template.portal.sample.todosamplefeature.apis.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.inacioferrarini.template.portal.sample.core.api.dtos.RestPage;
import com.inacioferrarini.template.portal.sample.security.dtos.JWTTokenDto;
import com.inacioferrarini.template.portal.sample.todosamplefeature.dtos.TodoDto;
import com.inacioferrarini.template.portal.sample.todosamplefeature.dtos.TodoDto.StatusType;

public interface TodoApiService {

	ResponseEntity<RestPage<TodoDto>> findAll(
		JWTTokenDto userToken,
		String name,
		String description,
		int page,
		int size
	);

	ResponseEntity<TodoDto> findById(
		JWTTokenDto userToken,
		Long id
	);

	ResponseEntity<TodoDto> create(
		JWTTokenDto userToken,
		String name,
		String description,
		StatusType status
	);

	ResponseEntity<TodoDto> update(
		JWTTokenDto userToken,
		Long id,
		String name,
		String description,
		StatusType status
	);

	ResponseEntity<String> delete(
		JWTTokenDto userToken,
		Long id
	);

	ResponseEntity<String> deleteMany(
		JWTTokenDto userToken,
		List<Long> idList
	);

}
