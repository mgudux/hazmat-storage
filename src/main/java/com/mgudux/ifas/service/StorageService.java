package com.mgudux.ifas.service;

import com.mgudux.ifas.domain.dto.StorageDto;
import com.mgudux.ifas.domain.entity.Company;
import com.mgudux.ifas.domain.entity.enums.MeasurementUnit;
import com.mgudux.ifas.domain.entity.enums.StorageType;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StorageService {
    List<StorageDto.Summary> listStorages();
    StorageDto.Summary createStorage(StorageDto.Request request);
    void deleteStorage(Long id);
    StorageDto.Detail updateStorage(Long id, StorageDto.Request request);
    List<StorageDto.Summary> getStoragesByType(StorageType storageType);
    List<StorageDto.Summary> getStoragesByYear(int constructionYear, Sort sort);
    List<StorageDto.Summary> getStoragesByCapacity(Double capacity, MeasurementUnit capacityUnit, Sort sort);
    List<StorageDto.Summary> getStoragesByCompany(Company company);


}
