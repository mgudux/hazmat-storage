package com.mgudux.ifas.repository;

import com.mgudux.ifas.domain.entity.Company;
import com.mgudux.ifas.domain.entity.enums.IndustryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    // Exakte Suche (Name ODER Adresse müssen zu 100% stimmen)
    List<Company> findByNameOrAddress(String name, String address);

    // Unscharfe Suche (Teil-Strings, ignoriert Groß- und kleinschreibung)
    List<Company> findByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String name, String address);

    List<Company> findAllByIndustryType(IndustryType industryType);
}
