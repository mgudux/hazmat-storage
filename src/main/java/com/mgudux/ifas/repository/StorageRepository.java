package com.mgudux.ifas.repository;

import com.mgudux.ifas.domain.entity.Company;
import com.mgudux.ifas.domain.entity.Storage;
import com.mgudux.ifas.domain.entity.enums.MeasurementUnit;
import com.mgudux.ifas.domain.entity.enums.StorageType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    List<Storage> findAllByStorageType(StorageType storageType);
    List<Storage> findAllByConstructionYear(int constructionYear, Sort sort);
    List<Storage> findAllByCapacityGreaterThanEqualAndCapacityUnit(Double capacity, MeasurementUnit capacityUnit, Sort sort);
    List<Storage> findAllByCompany(Company company);
}
