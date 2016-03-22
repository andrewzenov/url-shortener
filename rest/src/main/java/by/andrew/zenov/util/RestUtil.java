package by.andrew.zenov.util;

import by.andrew.zenov.exception.EntityNotFoundException;

public class RestUtil {

	public static void validation(Class<?> entityClass, Object entity) {
		if (entity == null) {
			throw new EntityNotFoundException(entityClass);
		}
	}

	public static void validation(Class<?> entityClass, Object entity, Object key) {
		if (entity == null) {
			throw new EntityNotFoundException(entityClass, key);
		}
	}

}
