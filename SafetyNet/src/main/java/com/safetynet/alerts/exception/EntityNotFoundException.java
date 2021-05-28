package com.safetynet.alerts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends SafetyNetException {

	private static final String MESSAGE = "Entity %s was not found for identifier '%s'";

	public EntityNotFoundException(Class<?> classe, String id) {
		super(String.format(MESSAGE, classe.getSimpleName(), id));
	}

}
