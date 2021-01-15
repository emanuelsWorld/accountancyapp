package ro.nexttech.internship.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.dto.UserDto;
import ro.nexttech.internship.pojo.UserPojo;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Optional<UserPojo> getByUserName(String user);

    List<User> findAll(Specification<User> specification);

    boolean saveUser(User user);

    boolean saveUserDto(UserDto userDto);

    User getUserFromDto(UserDto userDto);

    User findByUserName(String userName);

    boolean deleteUser(int id, String userName);

    User findUserById(int id);

    void updateUserFromDto(UserDto userDto, User user);

    UserDto getDtoFromUser (User user);

    List<UserDto> getDtoFromUserList (List<User> users);

    UserDto updateUser(int id, UserDto userDto);

}
