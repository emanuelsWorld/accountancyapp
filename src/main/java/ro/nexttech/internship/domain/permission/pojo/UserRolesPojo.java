package ro.nexttech.internship.domain.permission.pojo;

import ro.nexttech.internship.domain.permission.UserRole;

public class UserRolesPojo {
    private UserRole userRole;

    public UserRolesPojo(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
