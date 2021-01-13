package ro.nexttech.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.nexttech.internship.security.CustomUserDetailsService;
import ro.nexttech.internship.service.JwtService;

@RestController
@RequestMapping("sing-in")
public class SingupController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private CustomUserDetailsService customUserDetailsService;

    SingupController() {
    }

    @Autowired
    public SingupController(AuthenticationManager authenticationManager, JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }
}
