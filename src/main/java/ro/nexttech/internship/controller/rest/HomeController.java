package ro.nexttech.internship.controller.rest;

import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Invoice;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class HomeController {

    @GetMapping("/home")
    public String getHome() {
        return "Testhomepage";
    }


}
