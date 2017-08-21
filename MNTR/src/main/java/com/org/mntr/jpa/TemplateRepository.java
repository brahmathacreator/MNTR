package com.org.mntr.jpa;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import com.org.mntr.entity.Template;

public interface TemplateRepository extends DataTablesRepository<Template, Long> {

}
