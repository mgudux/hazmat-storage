package com.mgudux.ifas.domain.dto;

import com.mgudux.ifas.domain.entity.enums.HazardClass;
import com.mgudux.ifas.domain.entity.enums.MeasurementUnit;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public interface ContentDto {
        record Request(
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
    record Summary(
            Long id,
            String name,
            HazardClass hazard,
            Double amount,
            MeasurementUnit amountUnit,
            LocalDateTime expirationDate

            ) {

    }
    record Detail(
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
