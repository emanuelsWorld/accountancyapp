package ro.nexttech.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.dto.AuthResponseDto;
import ro.nexttech.internship.dto.UserRequestDto;
import ro.nexttech.internship.exception.UserNotFoundException;
import ro.nexttech.internship.security.CustomUserDetailsService;
import ro.nexttech.internship.service.JwtService;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private CustomUserDetailsService customUserDetailsService;

    public LoginController() {
    }

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping
    AuthResponseDto authenticate(@RequestBody @Valid UserRequestDto userRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userRequest.getUsername(), userRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new UserNotFoundException("User not found of bad credentials");
        }
        return new AuthResponseDto(jwtService.generateToken(customUserDetailsService.loadUserByUsername(userRequest.getUsername())));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/test-jwt")
    public String getAuthenticationManager() {
        return "Hello JWT";
    }
}
