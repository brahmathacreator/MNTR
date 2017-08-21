package com.org.mntr.service;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

public interface GenericService<T, T1> {

	public DataTablesOutput<T1> getAllData(DataTablesInput dtInput, Long eId) throws Exception;

	public DataTablesOutput<T1> getAllData(DataTablesInput dtInput) throws Exception;

	public T getDataByKeyAndStatus(Long id) throws Exception;

	public T getDataByKeyAndStatusAndExcludeKey(Long id, Long eId) throws Exception;

	public void saveOrEdit(T object) throws Exception;

	public void delete(Long rcdKey) throws Exception;

}
