package ro.nexttech.internship.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.domain.UserRole;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersControllerWeb {

    @GetMapping("home")
    public String getHome(Principal principal, Model model) {
        model.addAttribute("user", getSingleUser());
        return "index";
    }

    //mock Test
    public User getSingleUser() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("TestUser");
        user.setPassword("TestPassword");
        user.setRole(UserRole.MANAGER);
        return user;
    }

    public List<User> getUsersList(){
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserId(2);
        user.setUserName("TestUser2");
        user.setPassword("TestPassword2");
        users.add(getSingleUser());
        users.add(user);
        return users;
    }
}
