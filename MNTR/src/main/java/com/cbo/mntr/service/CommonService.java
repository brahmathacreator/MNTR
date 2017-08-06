package com.cbo.mntr.service;

public interface CommonService<T> {

	public T getDataByKeyAndStatus(Long id) throws Exception;

	public T getDataByKeyAndStatusAndExcludeKey(Long id, Long eId) throws Exception;

	public void save(T object, Long userKey) throws Exception;

	public void edit(T object, Long userKey) throws Exception;

	public void delete(Long rcdKey) throws Exception;

}
