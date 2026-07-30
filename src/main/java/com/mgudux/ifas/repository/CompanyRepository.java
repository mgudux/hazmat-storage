package com.mgudux.ifas.repository;

import com.mgudux.ifas.domain.entity.Company;
import com.mgudux.ifas.domain.entity.enums.IndustryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    // Unscharfe Suche (Teil-Strings, ignoriert Groß- und kleinschreibung)
    List<Company> findByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String name, String address);

    List<Company> findAllByIndustryType(IndustryType industryType);
}
