package com.org.mntr.jpa;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.org.mntr.entity.Category;

public interface CategoryRepository extends DataTablesRepository<Category, Long> {

}
