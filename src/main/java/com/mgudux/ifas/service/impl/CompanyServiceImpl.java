package com.mgudux.ifas.service.impl;

import com.mgudux.ifas.domain.dto.CompanyDto;
import com.mgudux.ifas.domain.entity.Company;
import com.mgudux.ifas.domain.entity.enums.IndustryType;
import com.mgudux.ifas.exception.ResourceAlreadyExistsException;
import com.mgudux.ifas.exception.ResourceNotFoundException;
import com.mgudux.ifas.mapper.CompanyMapper;
import com.mgudux.ifas.repository.CompanyRepository;
import com.mgudux.ifas.repository.CompanySpecifications;
import com.mgudux.ifas.service.CompanyService;
import jakarta.validation.ValidationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyMapper companyMapper, CompanyRepository companyRepository) {
        this.companyMapper = companyMapper;
        this.companyRepository = companyRepository;
    }


    @Override
    public List<CompanyDto.Summary> listCompanies() {
        return companyRepository.findAll().stream().map(companyMapper::toSummary).toList();
    }

    @Override
    public CompanyDto.Summary createCompany(CompanyDto.Request request) {
        if (request == null) {
            throw new ValidationException("Request cannot be null.");
        }
        if (companyRepository.existsByName(request.name())) {
            throw new ResourceAlreadyExistsException("A company with this name already exists");
        }
        Company company = new Company(
                request.name(),
                request.address(),
                request.industryType()
        );
        return companyMapper.toSummary(companyRepository.save(company));
    }

    @Override
    public void deleteCompany(Long id) {
        if (id == null) {
            throw new ValidationException("Company ID cannot be null.");
        }
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("No Company with this ID found.");
        }
        companyRepository.deleteById(id);
    }

    @Override
    public CompanyDto.Detail getCompanyById(Long id) {
        if (id == null) {
            throw new ValidationException("Company ID cannot be null.");
        }
        return companyMapper.toDetail(companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No Company with this ID found")));
    }

    @Override
    public List<CompanyDto.Summary> searchCompanies(String name, String address, IndustryType industryType) {
        Specification<Company> spec = Specification.where(
                CompanySpecifications.hasName(name))
                        .and(CompanySpecifications.hasAddress(address))
                        .and(CompanySpecifications.hasIndustryType(industryType));
        return companyRepository.findAll(spec).stream().map(companyMapper::toSummary).toList();
    }

    @Override
    public CompanyDto.Detail updateCompany(Long id, CompanyDto.Request request) {
        if (request == null) {
            throw new ValidationException("Request cannot be null.");
        }
        if (companyRepository.existsByNameIgnoreCaseAndIdNot(request.name(), id)) {
            throw new ResourceAlreadyExistsException("A company with this name already exists");
        }
        Company existingCompany = companyRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No Company with this ID found")
        );
        existingCompany.setName(request.name());
        existingCompany.setAddress(request.address());
        existingCompany.setIndustryType(request.industryType());
        return companyMapper.toDetail(companyRepository.save(existingCompany));
    }
}
