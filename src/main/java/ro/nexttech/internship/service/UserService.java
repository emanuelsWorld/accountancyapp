package ro.nexttech.internship.service;

import org.springframework.stereotype.Service;
import ro.nexttech.internship.pojo.UserPojo;

import java.util.Optional;

@Service
public interface UserService {

    Optional<UserPojo> getByUserName(String user);
}
