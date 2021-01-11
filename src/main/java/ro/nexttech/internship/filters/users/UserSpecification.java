package ro.nexttech.internship.filters.users;

import org.springframework.data.jpa.domain.Specification;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.filters.SearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification implements Specification<User> {

    private SearchCriteria criteria;

    public UserSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        String operation = criteria.getOperation();
        String key = criteria.getKey();
        Object value = criteria.getValue();

        if (operation.equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThan(root.get(key), value.toString());
        } else if (operation.equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThan(root.get(key), value.toString());
        } else if (operation.equalsIgnoreCase(":")) {
            if (root.get(key).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.get(key), "%" + value + "%");
            } else {
                return criteriaBuilder.equal(root.get(key), value);
            }
        }
        return null;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public String toString() {
        return "UserSpecification{" +
                "criteria=" + criteria +
                '}';
    }
}
