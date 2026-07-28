package com.mgudux.ifas.domain.dto;

import com.mgudux.ifas.domain.entity.enums.IndustryType;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public class CompanyDto {

    public record Request(
            @NotBlank(message = "Der Name der Company darf nicht leer sein") String name,
            @NotBlank(message = "Die Adresse der Company darf nicht leer sein") String address,
            @NotBlank(message = "Der Industrie Typ der Company darf nicht leer sein") IndustryType industryType
            ) {}

    public record Summary(
            Long id,
            String name,
            String address,
            IndustryType industryType
    ) {}

    public record Detail(
            Long id,
            String name,
            String address,
            IndustryType industryType,
            LocalDateTime updated,
            LocalDateTime created,
            List<StorageDto.Summary> storages
    ) {}
}
