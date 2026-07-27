package com.mgudux.ifas.domain.entity;


import com.mgudux.ifas.domain.entity.enums.IndustryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Company benötigt einen Namen!")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Company benötigt eine Adresse!")
    @Column(nullable = false)
    private String address;

    @NotNull(message = "Company benötigt eine Industriespezifikation!")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IndustryType industry;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updated;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Storage> storages = new ArrayList<>();



    public void addStorage(Storage storage) {
        this.storages.add(storage);
        storage.setCompany(this);
    }

    public void removeStorage(Storage storage) {
        this.storages.remove(storage);
        storage.setCompany(null);
    }

    public Company() {
    }

    public Company(String address, String name, IndustryType industry) {
        this.address = address;
        this.name = name;
        this.industry = industry;
    }

    public List<Storage> getStorages() {
        return storages;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public IndustryType getIndustry() {
        return industry;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIndustry(IndustryType industry) {
        this.industry = industry;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", industry=" + industry +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
