package ro.nexttech.internship.filters.firms;

import org.springframework.data.jpa.domain.Specification;
import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.filters.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FirmSpecificationBuilder {

    private final List<SearchCriteria> criteriaList;

    public FirmSpecificationBuilder() {
        criteriaList = new ArrayList<>();
    }

    public FirmSpecificationBuilder with(String key, String operation, Object value) {
        criteriaList.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Firm> build() {
        if (criteriaList.isEmpty()) {
            return null;
        }

        List<FirmSpecification> specs = criteriaList.stream()
                .map(FirmSpecification::new)
                .collect(Collectors.toList());
        Specification<Firm> result = specs.get(0);

        for (int i = 1; i < criteriaList.size(); i++) {
                result = Specification.where(result).and(specs.get(i));
        }

        return result;
    }

    public static Specification<Firm> getFirmSpec(String search) {
        FirmSpecificationBuilder builder = new FirmSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)([:]|[<]|[>])(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        return builder.build();
    }
}
