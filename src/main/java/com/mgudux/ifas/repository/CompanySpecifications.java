package com.mgudux.ifas.repository;

import com.mgudux.ifas.domain.entity.Company;
import com.mgudux.ifas.domain.entity.enums.IndustryType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class CompanySpecifications {

    // Utility class
    private CompanySpecifications() {}

    public static Specification<Company> hasName(String name) {
        return ((root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(name)) {
                return null;
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"
            );

        });
    }

    public static Specification<Company> hasAddress(String address) {

        return ((root, query, criteriaBuilder) -> {
           if (!StringUtils.hasText(address)) {
               return null;
           }
           return criteriaBuilder.like(
                   criteriaBuilder.lower(root.get("address")), "%" + address.toLowerCase() + "%"
           );
        });
    }

    public static Specification<Company> hasIndustryType(IndustryType industryType) {
        return (root, query, criteriaBuilder) -> {
            if (industryType == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("industryType"), industryType);
        };
    }
}
