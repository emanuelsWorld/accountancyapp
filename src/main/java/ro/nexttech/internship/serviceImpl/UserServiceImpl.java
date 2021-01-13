package ro.nexttech.internship.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.dto.UserDto;
import ro.nexttech.internship.pojo.UserPojo;
import ro.nexttech.internship.repository.UserRepository;
import ro.nexttech.internship.service.FirmService;
import ro.nexttech.internship.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private FirmService firmService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setFirmService(FirmService firmService) {
        this.firmService = firmService;
    }

    @Override
    public Optional<UserPojo> getByUserName(String user) {
        return userRepository.findByUserName(user).map(User::toUser);
    }

    @Override
    public List<User> findAll(Specification<User> specification) {
        return userRepository.findAll(specification);
    }

    @Override
    public boolean saveUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveUserDto(UserDto userDto) {
        try {
            userRepository.save(getUserFromDto(userDto));
            return true;
        }
        catch  (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else
            return null;
    }

    @Override
    public void updateUserFromDto(UserDto userDto, User user) {
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }

        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }

        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }

        if (userDto.getUserName() != null) {
            user.setUserName(userDto.getUserName());
        }

        if (userDto.getUserPassword() != null) {
            user.setUserPassword(userDto.getUserPassword());
        }

        if (userDto.getUserRole() != null) {
            user.setUserRole(userDto.getUserRole());
        }

        if (userDto.isActive()) {
            user.setActive(userDto.isActive());
        }

        if (userDto.getFirmId() != 0) {
            user.setFirm(firmService.findById(userDto.getFirmId()));
        }
    }

    @Override
    public UserDto getDtoFromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserPassword(user.getUserPassword());
        userDto.setEmail(user.getEmail());
        userDto.setUserRole(user.getUserRole());
        userDto.setFirmId(user.getFirm().getFirmId());
        userDto.setActive(user.isActive());
        return userDto;
    }

    @Override
    public List<UserDto> getDtoFromUserList(List<User> users) {
        return users.stream()
                .map(this::getDtoFromUser)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(int id, UserDto userDto) {
        User user = findUserById(id);
        updateUserFromDto(userDto,user);
        saveUser(user);
        return getDtoFromUser(user);
    }

    @Override
    public User getUserFromDto(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserPassword(userDto.getUserPassword());
        user.setEmail(userDto.getEmail());
        user.setUserRole(userDto.getUserRole());
        user.setFirm(firmService.findById(userDto.getFirmId()));
        user.setActive(userDto.isActive());
        return user;
    }

}
