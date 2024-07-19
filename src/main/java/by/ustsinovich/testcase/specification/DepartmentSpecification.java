package by.ustsinovich.testcase.specification;

import by.ustsinovich.testcase.entity.Department;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DepartmentSpecification {

    private DepartmentSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<Department> filterBy(String name, String location) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }

            if (location != null) {
                predicates.add(criteriaBuilder.like(root.get("location"), "%" + name + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
