package ro.nexttech.internship.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Invoice;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class HomeController {

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/home")
    public String getHome() {
        return "Testhomepage";
    }


}
