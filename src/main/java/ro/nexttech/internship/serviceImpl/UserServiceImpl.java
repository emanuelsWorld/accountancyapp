package ro.nexttech.internship.serviceImpl;

import org.springframework.stereotype.Component;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.pojo.UserPojo;
import ro.nexttech.internship.repository.UserRepository;
import ro.nexttech.internship.service.UserService;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserPojo> getByUserName(String user) {
        return userRepository.findByUserName(user).map(User::toUser);
    }
}
