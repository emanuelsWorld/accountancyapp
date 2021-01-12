package ro.nexttech.internship.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.nexttech.internship.pojo.UserPojo;
import ro.nexttech.internship.service.UserService;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserPojo> user = userService.getByUserName(username);
        if (user.isPresent()) {
            return new CustomUserDetails(user.get());
        } else throw new UsernameNotFoundException("UserName or Password is incorrect!");

    }
}
