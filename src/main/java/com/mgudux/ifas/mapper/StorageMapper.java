package com.mgudux.ifas.mapper;

import com.mgudux.ifas.domain.dto.StorageDto;
import com.mgudux.ifas.domain.entity.Storage;

public interface StorageMapper {

    StorageDto.Summary toSummary(Storage storage);
    StorageDto.Detail toDetail(Storage storage);

    Storage toEntity(StorageDto.Request request);
}
