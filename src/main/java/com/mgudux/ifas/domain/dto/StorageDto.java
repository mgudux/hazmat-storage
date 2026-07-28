package com.mgudux.ifas.domain.dto;

import com.mgudux.ifas.domain.entity.enums.MeasurementUnit;
import com.mgudux.ifas.domain.entity.enums.StorageType;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.List;

public class StorageDto {

    public record Request(
            @NotNull(message = "Storage requires a Storage Type!") StorageType storageType,
            @NotBlank(message = "Storage requires a location!") String location,
            @Min(value = 1800, message = "Storage requires a construction year") int constructionYear,
            @Positive(message = "Storage requires a capacity greater than 0!") double capacity,
            @NotNull(message = "Storage requires a capacity unit!") MeasurementUnit capacityUnit,
            @NotNull(message = "Storage requires a date of the last check") LocalDateTime lastCheck,

            @Range(min = 1, max = 120, message = "The check intervall in months must be between 1 and 120")
            int monthsCheckIntervall
            ) {}

    public record Summary(
            Long id,
            StorageType storageType,
            String location,
            double capacity,
            MeasurementUnit capacityUnit,
            LocalDateTime lastCheck
    ) {}

    public record Detail(
            Long id,
            StorageType storageType,
            String location,
            int constructionYear,
            double capacity,
            MeasurementUnit capacityUnit,
            LocalDateTime lastCheck,
            int monthsCheckIntervall,
            LocalDateTime updated,
            LocalDateTime created,
            CompanyDto.Summary company,
            List<ContentDto.Summary> contents
            ) {}
}
