package ro.nexttech.internship.dto;

import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.domain.permission.UserRole;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {

    private int userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String userPassword;
    private String email;
    private String userRole;
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() { return userRole; }

    public void setUserRole(String role) {
        this.userRole = role;
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

}
