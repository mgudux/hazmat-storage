package com.mgudux.ifas.repository;

import com.mgudux.ifas.domain.entity.Company;
import com.mgudux.ifas.domain.entity.enums.IndustryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Optional<Company> findByNameOrAddress(String name, String address);
    List<Company> findAllByIndustryType(IndustryType industryType);
}
