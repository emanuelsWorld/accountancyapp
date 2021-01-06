package ro.nexttech.internship.domain;


import javax.persistence.*;

//@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name="user_id")
    private int userId;
    @Column(name="user_name")
    private String userName;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @ManyToOne
    @JoinColumn(name="firm_id", nullable = false)
    private Firm firm;
    private boolean isActive;

    public User(){

    }

    public User(int userId, String userName, String firstName, String lastName, String password, String email, UserRole role, Firm firm, boolean isActive) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.role = role;
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
