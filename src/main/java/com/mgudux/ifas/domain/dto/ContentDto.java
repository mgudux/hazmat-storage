package com.mgudux.ifas.domain.dto;

import com.mgudux.ifas.domain.entity.enums.HazardClass;
import com.mgudux.ifas.domain.entity.enums.MeasurementUnit;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class ContentDto {

    public record Request(
            @NotBlank(message = "Name of the content is required")
            String name,

            HazardClass hazardClass,

            @NotNull(message = "Content amount cannot be null")
            @Min(value = 0, message = "Content amount cannot be negative")
            Double amount,

            @NotNull(message = "Content requires a measurement unit")
            MeasurementUnit amountUnit,

            LocalDateTime expirationDate

            ) {

    }
    public record Summary(
            Long id,
            String name,
            HazardClass hazard,
            Double amount,
            MeasurementUnit amountUnit,
            LocalDateTime expirationDate

            ) {

    }
    public record Detail(
            Long id,
            String name,
            HazardClass hazard,
            Double amount,
            MeasurementUnit amountUnit,
            LocalDateTime expirationDate,
            LocalDateTime updated,
            LocalDateTime created,
            StorageDto.Summary storage
    ) {}
}
