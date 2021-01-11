package ro.nexttech.internship.dto;

import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.domain.permission.UserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto {

    private int userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private UserRole role;
    private int firmId;
    private boolean isActive;

    public UserDto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }

    public static UserDto getDtoFromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getUserPassword());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getUserRole());
        userDto.setFirmId(user.getFirm().getFirmId());
        userDto.setActive(user.isActive());
        return userDto;
    }

    public static List<UserDto> getDtoFromUserList(List<User> users) {
        return users.stream()
                .map(UserDto::getDtoFromUser)
                .collect(Collectors.toList());
    }
}
