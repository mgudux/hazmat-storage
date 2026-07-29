package com.mgudux.ifas.domain.dto;

import com.mgudux.ifas.domain.entity.Company;
import com.mgudux.ifas.domain.entity.enums.IndustryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public interface CompanyDto {

    record Request(
            @NotBlank(message = "Der Name der Company darf nicht leer sein!") String name,
            @NotBlank(message = "Die Adresse der Company darf nicht leer sein!") String address,
            @NotNull(message = "Der Industrie Typ der Company darf nicht leer sein") IndustryType industryType
            ) {}

    record Summary(
            Long id,
            String name,
            String address,
            IndustryType industryType
    ) {
        public static Summary fromEntity(Company company) {
            if (company == null) {
                return null;
            }
            return new Summary(
                    company.getId(),
                    company.getName(),
                    company.getAddress(),
                    company.getIndustryType()
            );
        }
    }

    record Detail(
            Long id,
            String name,
            String address,
            IndustryType industryType,
            LocalDateTime updated,
            LocalDateTime created,
            List<StorageDto.Summary> storages
    ) {}
}
