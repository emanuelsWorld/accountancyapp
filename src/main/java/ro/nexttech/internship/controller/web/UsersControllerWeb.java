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
        return "index";
    }


}
