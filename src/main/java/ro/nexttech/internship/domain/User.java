package ro.nexttech.internship.domain;


import ro.nexttech.internship.pojo.UserPojo;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue
    private int userId;
    @Column(name="user_name")
    private String userName;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="user_password")
    private String userPassword;
    @Column(name="email")
    private String email;
    @Column(name="user_role")
    private String userRole;
    @ManyToOne
    @JoinColumn(name="firm_id", nullable = false)
     private Firm firm;
    private boolean isActive;

    public User(){

    }

    public UserPojo toUser() {
        UserPojo user = new UserPojo();
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserRole(userRole);
        user.setActive(isActive);
        return user;
    }

    public User(int userId, String userName, String firstName, String lastName, String userPassword, String email, String userRole, Firm firm, boolean isActive) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPassword = userPassword;
        this.email = email;
        this.userRole = userRole;
        this.firm = firm;
        this.isActive = isActive;
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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
