package com.mgudux.ifas.mapper.impl;

import com.mgudux.ifas.domain.dto.CompanyDto;
import com.mgudux.ifas.domain.entity.Company;
import com.mgudux.ifas.mapper.CompanyMapper;
import com.mgudux.ifas.mapper.StorageMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapperImpl implements CompanyMapper {

    private final StorageMapper storageMapper;

    public CompanyMapperImpl(StorageMapper storageMapper) {
        this.storageMapper = storageMapper;
    }

    @Override
    public CompanyDto.Summary toSummary(Company company) {
        if (company == null) {
            return null;
        }
        return new CompanyDto.Summary(
                company.getId(),
                company.getName(),
                company.getAddress(),
                company.getIndustryType()
        );
    }

    @Override
    public CompanyDto.Detail toDetail(Company company) {
        if (company == null) {
            return null;
        }
        return new CompanyDto.Detail(
                company.getId(),
                company.getName(),
                company.getAddress(),
                company.getIndustryType(),
                company.getUpdated(),
                company.getCreated(),
                company.getStorages().stream().map(storageMapper::toSummary).toList()
        );

    }

    @Override
    public Company toEntity(CompanyDto.Request request) {
        if (request == null) {
            return null;
        }
        Company company = new Company();
        company.setName(request.name());
        company.setAddress(request.address());
        company.setIndustryType(request.industryType());
        return company;
    }
}
