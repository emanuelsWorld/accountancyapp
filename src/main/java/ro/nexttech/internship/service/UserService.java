package ro.nexttech.internship.service;

import org.springframework.data.jpa.domain.Specification;
import ro.nexttech.internship.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAll(Specification<User> specification);
}
