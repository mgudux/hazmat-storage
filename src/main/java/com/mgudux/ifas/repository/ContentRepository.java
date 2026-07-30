package com.mgudux.ifas.repository;

import com.mgudux.ifas.domain.entity.Content;
import com.mgudux.ifas.domain.entity.enums.HazardClass;
import com.mgudux.ifas.domain.entity.enums.MeasurementUnit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    Optional<Content> findByName (String name);
    List<Content> findByHazardClass (HazardClass hazardClass);
    List<Content> findByAmountGreaterThanEqualAndAmountUnit(Double amount, MeasurementUnit amountUnit);
    List<Content> findByExpirationDateGreaterThanEqual(LocalDateTime expirationDate, Sort sort);
}
