package com.mgudux.ifas.service;

import com.mgudux.ifas.domain.dto.CompanyDto;
import com.mgudux.ifas.domain.entity.enums.IndustryType;

import java.util.List;

/**
 * Verwaltung der Firmenstammdaten
 */
public interface CompanyService {

    List<CompanyDto.Summary> listCompanies();

    /**
     * @param request Die Daten der neu anzulegenden Firma
     * @return Die Zusammenfassung der angelegten Firma inklusive ID
     * @throws , wenn eine Firma mit identischen Namen und Addresse existiert
     */
    CompanyDto.Summary createCompany(CompanyDto.Request request);

    /**
     * @throws com.mgudux.ifas.exception.ResourceNotFoundException wenn die ID nicht existiert
     * @throws
     */
    void deleteCompany(long id);
    CompanyDto.Detail getCompanyById(long id);
    List<CompanyDto.Summary> searchCompanies(String name, String address, IndustryType industryType);
    CompanyDto.Detail updateCompany(long id, CompanyDto.Request request);
}
