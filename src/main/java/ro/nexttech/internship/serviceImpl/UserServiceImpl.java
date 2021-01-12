package ro.nexttech.internship.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.repository.UserRepository;
import ro.nexttech.internship.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll(Specification<User> specification) {
        return userRepository.findAll(specification);
    }
}
