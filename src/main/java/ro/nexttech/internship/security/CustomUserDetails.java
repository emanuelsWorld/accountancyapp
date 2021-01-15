package ro.nexttech.internship.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ro.nexttech.internship.pojo.UserPojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private UserPojo user;

    public CustomUserDetails(UserPojo user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> userRoles = new ArrayList<>();
        String[] splitRoles = user.getUserRole().split(";");
        for (String role : splitRoles) {
            SimpleGrantedAuthority grantedAuthoritiesRole = new SimpleGrantedAuthority("ROLE_" + role);
            userRoles.add(grantedAuthoritiesRole);
        }
        return userRoles;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }
}
