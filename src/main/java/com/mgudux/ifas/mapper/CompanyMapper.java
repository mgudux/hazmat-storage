package com.mgudux.ifas.mapper;

import com.mgudux.ifas.domain.dto.CompanyDto;
import com.mgudux.ifas.domain.entity.Company;

public interface CompanyMapper {

    CompanyDto.Summary toSummary(Company company);
    CompanyDto.Detail toDetail(Company company);
    Company toEntity(CompanyDto.Request request);

}
