package com.mgudux.ifas.services.impl;

import com.mgudux.ifas.domain.dto.CompanyDto;
import com.mgudux.ifas.domain.entity.Company;
import com.mgudux.ifas.domain.entity.enums.IndustryType;
import com.mgudux.ifas.mapper.CompanyMapper;
import com.mgudux.ifas.repository.CompanyRepository;
import com.mgudux.ifas.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CompanyMapper companyMapper;
    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    void createCompany_Success() {

        // Datenvorbereiten
        CompanyDto.Request request = new CompanyDto.Request(
                "Firmenname", "Firmenadresse", IndustryType.CHEMICAL);
        Company entityToSave = new Company(
                "Firmenname", "Firmenadresse", IndustryType.CHEMICAL);
        CompanyDto.Summary expectedResponseDto = new CompanyDto.Summary(
                1L, "Firmenname", "Firmenadresse", IndustryType.CHEMICAL);

        // Erwartete Verhalten, wenn wir Mocks aufrufen
        when(companyRepository.existsByName("Firmenname")).thenReturn(false);
        when(companyRepository.save(any(Company.class))).thenReturn(entityToSave);
        when(companyMapper.toSummary(entityToSave)).thenReturn(expectedResponseDto);

        // Ausführung
        CompanyDto.Summary result = companyService.createCompany(request);

        // Überprüfen
        assertNotNull(result);
        assertEquals("Firmenname", result.name());
        verify(companyRepository, times(1)).save(any(Company.class));
    }
}
