package com.mgudux.ifas.controller;

import com.mgudux.ifas.domain.dto.CompanyDto;
import com.mgudux.ifas.domain.entity.enums.IndustryType;
import com.mgudux.ifas.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto.Summary>> listCompanies() {
        return ResponseEntity.ok(companyService.listCompanies());
    }

    @PostMapping
    public ResponseEntity<CompanyDto.Summary> createCompany(@Valid @RequestBody CompanyDto.Request request) {
        CompanyDto.Summary createdCompany = companyService.createCompany(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCompany.id())
                .toUri();
        return ResponseEntity.created(location).body(createdCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto.Detail> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CompanyDto.Summary>> searchCompanies(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) IndustryType industryType
            ) {
        return ResponseEntity.ok(companyService.searchCompanies(name, address, industryType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto.Detail> updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody CompanyDto.Request request
    ) {
        return ResponseEntity.ok(companyService.updateCompany(id, request));
    }


}
