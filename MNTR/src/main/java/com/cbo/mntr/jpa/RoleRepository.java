package com.cbo.mntr.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cbo.mntr.entity.UserRole;

public interface RoleRepository extends PagingAndSortingRepository<UserRole, Long> {

}
