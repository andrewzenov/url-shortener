package by.andrew.zenov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(Class<?> entityClass, Object key) {
		super(String.format("Could not find %s with key %s.", entityClass.getSimpleName(), key));
	}

	public EntityNotFoundException(Class<?> entityClass) {
		super(String.format("Could not find %s.", entityClass.getSimpleName()));
	}

}
