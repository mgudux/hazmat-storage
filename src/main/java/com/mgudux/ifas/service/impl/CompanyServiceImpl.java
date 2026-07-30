package com.mgudux.ifas.service.impl;

import com.mgudux.ifas.domain.dto.CompanyDto;
import com.mgudux.ifas.domain.entity.enums.IndustryType;
import com.mgudux.ifas.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Override
    public List<CompanyDto.Summary> listCompanies() {
        return List.of();
    }

    @Override
    public CompanyDto.Summary createCompany(CompanyDto.Request request) {
        return null;
    }

    @Override
    @SuppressWarnings("unused")
    public void deleteCompany(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CompanyDto.Detail getCompanyById(long id) {
        return null;
    }

    @Override
    public List<CompanyDto.Summary> searchCompanies(String name, String address, IndustryType industryType) {
        return List.of();
    }

    @Override
    public CompanyDto.Detail updateCompany(long id, CompanyDto.Request request) {
        return null;
    }
}
