package com.cbo.mntr.dao.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.Session;

public abstract class EntityManagerAbstractDAO<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public EntityManagerAbstractDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@PersistenceContext
	private EntityManager entityManager;

	public T getByKey(PK key) {
		return (T) entityManager.find(persistentClass, key);
	}

	public void save(T entity) {
		entityManager.persist(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	protected Criteria createEntityCriteria() {
		return entityManager.unwrap(Session.class).createCriteria(persistentClass);
	}

	protected CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}

}
