package ro.nexttech.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.nexttech.internship.dto.UserRegistrationDto;
import ro.nexttech.internship.exception.DuplicateUserException;
import ro.nexttech.internship.exception.FirmNotFoundException;
import ro.nexttech.internship.pojo.UserPojo;
import ro.nexttech.internship.security.CustomUserDetailsService;
import ro.nexttech.internship.service.JwtService;
import ro.nexttech.internship.serviceImpl.UserServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserServiceImpl userService;

    RegistrationController() {
    }

    @Autowired
    public RegistrationController(AuthenticationManager authenticationManager, JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping
    UserPojo registration(@RequestBody @Valid UserRegistrationDto userRegistrationDto) throws FirmNotFoundException, DuplicateUserException {
        return userService.register(userRegistrationDto);
    }

}
