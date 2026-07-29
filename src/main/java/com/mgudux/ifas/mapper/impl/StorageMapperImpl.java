package com.mgudux.ifas.mapper.impl;

import com.mgudux.ifas.domain.dto.CompanyDto;
import com.mgudux.ifas.domain.dto.ContentDto;
import com.mgudux.ifas.domain.dto.StorageDto;
import com.mgudux.ifas.domain.entity.Storage;
import com.mgudux.ifas.mapper.StorageMapper;
import org.springframework.stereotype.Component;

@Component
public class StorageMapperImpl implements StorageMapper {

    @Override
    public StorageDto.Summary toSummary(Storage storage) {
        if (storage == null) {
            return null;
        }
        return new StorageDto.Summary(
                storage.getId(),
                storage.getStorageType(),
                storage.getLocation(),
                storage.getCapacity(),
                storage.getCapacityUnit(),
                storage.getLastCheck()
        );
    }

    @Override
    public StorageDto.Detail toDetail(Storage storage) {
        if (storage == null) {
            return null;
        }


        return new StorageDto.Detail(
                storage.getId(),
                storage.getStorageType(),
                storage.getLocation(),
                storage.getConstructionYear(),
                storage.getCapacity(),
                storage.getCapacityUnit(),
                storage.getLastCheck(),
                storage.getMonthsCheckIntervall(),
                storage.getUpdated(),
                storage.getCreated(),
                CompanyDto.Summary.fromEntity(storage.getCompany()),
                storage.getContents().stream().map(ContentDto.Summary::fromEntity).toList()
        );
    }

    @Override
    public Storage toEntity(StorageDto.Request request) {
        if (request == null) {
            return null;
        }
        Storage storage = new Storage();
        storage.setStorageType(request.storageType());
        storage.setLocation(request.location());
        storage.setConstructionYear(request.constructionYear());
        storage.setCapacity(request.capacity());
        storage.setCapacityUnit(request.capacityUnit());
        storage.setLastCheck(request.lastCheck());
        return storage;
    }
}
