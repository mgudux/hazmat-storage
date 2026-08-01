package com.mgudux.ifas.repository;

import com.mgudux.ifas.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
    boolean existsByName(String name);
}
