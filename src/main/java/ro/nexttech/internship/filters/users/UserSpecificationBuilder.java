package ro.nexttech.internship.filters.users;

import org.springframework.data.jpa.domain.Specification;
import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.filters.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserSpecificationBuilder {


    private final List<SearchCriteria> criteriaList;

    public UserSpecificationBuilder() {
        criteriaList = new ArrayList<>();
    }

    public UserSpecificationBuilder with(String key, String operation, Object value) {
        criteriaList.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<User> build(Firm firm) {
        if (criteriaList.isEmpty())
            return null;

        criteriaList.add(new SearchCriteria("firm",":", firm));
        List<UserSpecification> specs = criteriaList.stream()
                .map(UserSpecification::new)
                .collect(Collectors.toList());
        Specification<User> result = specs.get(0);

        for (int i = 1; i < criteriaList.size(); i++)
            result = Specification.where(result).and(specs.get(i));

        return result;
    }

    public static Specification<User> getUserSpec(String search, Firm firm) {
        UserSpecificationBuilder builder = new UserSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)([:]|[<]|[>])(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        return builder.build(firm);
    }

}
