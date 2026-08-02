package com.mgudux.ifas.domain.entity.enums;

public enum HazardClass {
    EXPLOSIVE(StorageType.SHELVES),
    FLAMMABLE(StorageType.PRESSURE),
    TOXIC(StorageType.CRYOGENIC),
    RADIOACTIVE(StorageType.COMPOST);

    private final StorageType requiredStorageType;

    HazardClass(StorageType requiredStorageType) {
        this.requiredStorageType = requiredStorageType;
    }

    public StorageType getRequiredStorageType() {
        return requiredStorageType;
    }

    // static da hazardClass null sein kann
    public static StorageType getRequiredStorageTypeFor(HazardClass hazardClass) {
        if (hazardClass == null) {
            return StorageType.SHELVES;
        }
        return hazardClass.getRequiredStorageType();
    }
}
