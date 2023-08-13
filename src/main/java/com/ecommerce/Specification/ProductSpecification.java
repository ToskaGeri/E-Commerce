package com.ecommerce.Specification;

import com.ecommerce.Models.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> getSpec(String name, String category) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !(name.isEmpty())) {
                predicates.add(criteriaBuilder.like(root.get("productName"), "%" + name + "%"));
            }
            if (category != null && !(category.isEmpty())) {
                predicates.add(criteriaBuilder.equal(root.get("productCategory"), category));
            }

            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        });
    }
}
