package by.andrew.zenov.data.dao.impl;

import by.andrew.zenov.data.dao.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

public abstract class AbstractDao<T, K> implements Dao<T, K> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> entityClass;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	@Override
	public T get(K id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public T get(K id, Map<String, Object> properties) {
		return entityManager.find(entityClass, id, properties);
	}

	@Override
	public List<T> getAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.select(root);
		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public void delete(K id) {
		T entity = entityManager.getReference(entityClass, id);
		entityManager.remove(entity);
	}

	@Override
	public void insert(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}
}
