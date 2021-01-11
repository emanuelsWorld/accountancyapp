package ro.nexttech.internship.filters.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ro.nexttech.internship.domain.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

}
