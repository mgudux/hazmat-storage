package com.mgudux.ifas.domain.entity;

import com.mgudux.ifas.domain.entity.enums.HazardClass;
import com.mgudux.ifas.domain.entity.enums.MeasurementUnit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Content benötigt einen Namen!")
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private HazardClass hazard;

    @Positive(message = "Content benötigt eine Menge größer als 0!")
    @Column(nullable = false)
    private double amount;

    @NotNull(message = "Content benötigt eine Einheit für die Menge")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MeasurementUnit amountUnit;

    private LocalDateTime expirationDate;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storageId", nullable = false)
    private Storage storage;

    public Content() {
    }

    public Content(String name, HazardClass hazard, double amount, MeasurementUnit amountUnit, LocalDateTime expirationDate) {
        this.name = name;
        this.hazard = hazard;
        this.amount = amount;
        this.amountUnit = amountUnit;
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HazardClass getHazard() {
        return hazard;
    }

    public double getAmount() {
        return amount;
    }

    public MeasurementUnit getAmountUnit() {
        return amountUnit;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHazard(HazardClass hazard) {
        this.hazard = hazard;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setAmountUnit(MeasurementUnit amountUnit) {
        this.amountUnit = amountUnit;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return Objects.equals(id, content.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hazard=" + hazard +
                ", amount=" + amount +
                ", amountUnit=" + amountUnit +
                ", expirationDate=" + expirationDate +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
