package com.mgudux.ifas.domain.entity;

import com.mgudux.ifas.domain.entity.enums.MeasurementUnit;
import com.mgudux.ifas.domain.entity.enums.StorageType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Storage benötigt eine Lagerspezifikation!")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StorageType storageType;

    private String location;

    @Min(value = 1800, message = "Storage benötigt ein Baujahr!")
    @Column(nullable = false)
    private int constructionYear;

    @Positive(message = "Kapazität muss größer sein als 0!")
    @Column(nullable = false)
    private double capacity;

    @NotNull(message = "Storage benötigt eine Einheit für die Kapazität")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MeasurementUnit capacityUnit;

    @NotNull(message = "Storage benötigt das Datum der letzten Überprüfung")
    @Column(nullable = false)
    private LocalDateTime lastCheck;

    @Positive(message = "Storage benötigt einen Intervall in Monaten für die Prüfungen!")
    @Column(nullable = false)
    private int monthsCheckIntervall;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "storage", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Content> contents = new ArrayList<>();

    public void addContent(Content content) {
        this.contents.add(content);
        content.setStorage(this);
    }

    public void removeContent(Content content) {
        this.contents.remove(content);
        content.setStorage(null);
    }

    public Storage() {
    }

    public Storage(StorageType storageType, String location, int constructionYear, double capacity, MeasurementUnit capacityUnit, LocalDateTime lastCheck, int monthsCheckIntervall) {
        this.storageType = storageType;
        this.location = location;
        this.constructionYear = constructionYear;
        this.capacity = capacity;
        this.capacityUnit = capacityUnit;
        this.lastCheck = lastCheck;
        this.monthsCheckIntervall = monthsCheckIntervall;
    }

    public Long getId() {
        return id;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public String getLocation() {
        return location;
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public double getCapacity() {
        return capacity;
    }

    public MeasurementUnit getCapacityUnit() {
        return capacityUnit;
    }

    public LocalDateTime getLastCheck() {
        return lastCheck;
    }

    public int getMonthsCheckIntervall() {
        return monthsCheckIntervall;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public Company getCompany() {
        return company;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setConstructionYear(int constructionYear) {
        this.constructionYear = constructionYear;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setCapacityUnit(MeasurementUnit capacityUnit) {
        this.capacityUnit = capacityUnit;
    }

    public void setLastCheck(LocalDateTime lastCheck) {
        this.lastCheck = lastCheck;
    }

    public void setMonthsCheckIntervall(int monthsCheckIntervall) {
        this.monthsCheckIntervall = monthsCheckIntervall;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(id, storage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", storageType=" + storageType +
                ", location='" + location + '\'' +
                ", constructionYear=" + constructionYear +
                ", capacity=" + capacity +
                ", capacityUnit=" + capacityUnit +
                ", lastCheck=" + lastCheck +
                ", monthsCheckIntervall=" + monthsCheckIntervall +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
