package ro.nexttech.internship.filters.invoices;

import org.springframework.data.jpa.domain.Specification;
import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.filters.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InvoiceSpecificationBuilder {

    private final List<SearchCriteria> criteriaList;

    public InvoiceSpecificationBuilder() {
        criteriaList = new ArrayList<>();
    }

    public InvoiceSpecificationBuilder with(String key, String operation, Object value) {
        criteriaList.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Invoice> build(Firm firm) {
        if (criteriaList.isEmpty()) {
            return null;
        }

        SearchCriteria searchCriteria = new SearchCriteria("firm",":", firm);
        criteriaList.add(searchCriteria);

        List<InvoiceSpecification> specs = criteriaList.stream()
                .map(InvoiceSpecification::new)
                .collect(Collectors.toList());

        Specification<Invoice> result = specs.get(0);

        for (int i = 1; i < criteriaList.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }

        return result;
    }

    public static Specification<Invoice> getInvoiceSpec(String search, Firm firm) {
        InvoiceSpecificationBuilder builder = new InvoiceSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)([:]|[<]|[>])((\\w+?)|(\\d{4}-\\d{2}-\\d{2})),");
        Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        return builder.build(firm);
    }


}

