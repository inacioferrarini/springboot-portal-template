package com.inacioferrarini.template.portal.sample.todosamplefeature.apis.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inacioferrarini.template.portal.sample.core.api.dtos.RestPage;
import com.inacioferrarini.template.portal.sample.core.api.factories.ApiRequestFactory;
import com.inacioferrarini.template.portal.sample.security.dtos.JWTTokenDto;
import com.inacioferrarini.template.portal.sample.todosamplefeature.apis.requests.DeleteManyTodoApiRequestDto;
import com.inacioferrarini.template.portal.sample.todosamplefeature.apis.requests.TodoDataApiRequestDto;
import com.inacioferrarini.template.portal.sample.todosamplefeature.apis.services.TodoApiService;
import com.inacioferrarini.template.portal.sample.todosamplefeature.dtos.TodoDto;
import com.inacioferrarini.template.portal.sample.todosamplefeature.dtos.TodoDto.StatusType;

@Service
public class TodoApiServiceImpl implements TodoApiService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiRequestFactory apiRequestFactory;

	@Override
	public ResponseEntity<RestPage<TodoDto>> findAll(
		JWTTokenDto userToken,
		String name,
		String description,
		int page,
		int size
	) {
		ParameterizedTypeReference<RestPage<TodoDto>> responseType = new ParameterizedTypeReference<RestPage<TodoDto>>() {
		};
		HttpEntity<String> apiRequest = apiRequestFactory.emptyRequestEntity(userToken);
		try {
			return restTemplate.exchange(
				"/todos?name={name}&description={description}&page={page}&size={size}",
				HttpMethod.GET,
				apiRequest,
				responseType,
				name, description, page, size
			);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ResponseEntity<TodoDto> findById(
		JWTTokenDto userToken,
		Long id
	) {
		HttpEntity<String> apiRequest = apiRequestFactory.emptyRequestEntity(userToken);
		try {
			return restTemplate.exchange(
				"/todos/{id}",
				HttpMethod.GET,
				apiRequest,
				TodoDto.class,
				id
			);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ResponseEntity<TodoDto> create(
		JWTTokenDto userToken,
		String name,
		String description,
		StatusType status
	) {
		TodoDataApiRequestDto todoApiRequest = new TodoDataApiRequestDto(name, description, status);
		HttpEntity<TodoDataApiRequestDto> apiRequest = apiRequestFactory.requestEntity(
			todoApiRequest,
			userToken
		);
		try {
			return restTemplate.postForEntity(
				"/todos",
				apiRequest,
				TodoDto.class
			);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ResponseEntity<TodoDto> update(
		JWTTokenDto userToken,
		Long id,
		String name,
		String description,
		StatusType status
	) {
		TodoDataApiRequestDto todoApiRequest = new TodoDataApiRequestDto(name, description, status);
		HttpEntity<TodoDataApiRequestDto> apiRequest = apiRequestFactory.requestEntity(
			todoApiRequest,
			userToken
		);
		try {
			return restTemplate.exchange(
				"/todos/{id}",
				HttpMethod.PUT,
				apiRequest,
				TodoDto.class,
				id
			);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ResponseEntity<String> delete(
		JWTTokenDto userToken,
		Long id
	) {
		HttpEntity<String> apiRequest = apiRequestFactory.emptyRequestEntity(userToken);
		try {
			return restTemplate.exchange(
				"/todos/{id}",
				HttpMethod.DELETE,
				apiRequest,
				String.class,
				id
			);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ResponseEntity<String> deleteMany(
		JWTTokenDto userToken,
		List<Long> idList
	) {
		DeleteManyTodoApiRequestDto todoApiRequest = new DeleteManyTodoApiRequestDto(idList);
		HttpEntity<DeleteManyTodoApiRequestDto> apiRequest = apiRequestFactory.requestEntity(
			todoApiRequest,
			userToken
		);
		try {
			return restTemplate.exchange(
				"/todos",
				HttpMethod.DELETE,
				apiRequest,
				String.class
			);
		} catch (Exception ex) {
			throw ex;
		}
	}

}
