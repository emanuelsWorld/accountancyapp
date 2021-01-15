package ro.nexttech.internship.filters.invoices;

import org.springframework.data.jpa.domain.Specification;
import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.filters.SearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceSpecification implements Specification<Invoice> {

    private SearchCriteria criteria;

    public InvoiceSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        String operation = criteria.getOperation();
        if (operation.equalsIgnoreCase(">")) {
            return getGreaterThanPredicate(root, criteriaBuilder);
        } else if (operation.equalsIgnoreCase("<")) {
            return getLessThanPredicate(root, criteriaBuilder);
        } else if (operation.equalsIgnoreCase(":")) {
            return getEqualPredicate(root, criteriaBuilder);
        }
        return null;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public boolean isDate(String date) {
        Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})");
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }

    public LocalDate getDateFromString(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));

        return LocalDate.of(year, month, day);
    }

    public Predicate getGreaterThanPredicate(Root<Invoice> root, CriteriaBuilder criteriaBuilder) {
        if (root.get(criteria.getKey()).getJavaType() == LocalDate.class) {
            return criteriaBuilder.greaterThan(root.get(criteria.getKey()), getDateFromString(criteria.getValue().toString()));
        }
        return criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
    }

    public Predicate getLessThanPredicate(Root<Invoice> root, CriteriaBuilder criteriaBuilder) {
        if (root.get(criteria.getKey()).getJavaType() == LocalDate.class) {
            return criteriaBuilder.lessThan(root.get(criteria.getKey()), getDateFromString(criteria.getValue().toString()));
        }
        return criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
    }

    public Predicate getEqualPredicate(Root<Invoice> root, CriteriaBuilder criteriaBuilder) {
        if (root.get(criteria.getKey()).getJavaType() == LocalDate.class) {
            criteria.setValue(getDateFromString(criteria.getValue().toString()));
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
        if (root.get(criteria.getKey()).getJavaType() == Firm.class) {
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
        try {
            Integer.parseInt(criteria.getValue().toString());
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return criteriaBuilder.like(
                root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
    }

    @Override
    public String toString() {
        return "InvoiceSpecification{" +
                "criteria=" + criteria +
                '}';
    }
}
