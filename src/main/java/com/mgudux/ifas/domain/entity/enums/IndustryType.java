package com.mgudux.ifas.domain.entity.enums;


import java.util.EnumSet;
import java.util.Set;

public enum IndustryType {
    LOGISTIC(EnumSet.of(StorageType.SHELVES, StorageType.PRESSURE)),
    CHEMICAL(EnumSet.of(StorageType.COMPOST, StorageType.CRYOGENIC));

    private final Set<StorageType> allowedStorageTypes;

    IndustryType(Set<StorageType> allowedStorageTypes) {
        this.allowedStorageTypes = allowedStorageTypes;
    }

    public boolean isStorageTypeAllowed(StorageType storageType) {
        return this.allowedStorageTypes.contains(storageType);
    }
}
