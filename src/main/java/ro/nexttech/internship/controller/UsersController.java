package ro.nexttech.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.dto.UserDto;
import ro.nexttech.internship.filters.users.UserSpecificationBuilder;
import ro.nexttech.internship.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDto> searchUsers(@RequestParam(value = "search") String search, Authentication auth) {
        User principal = userService.findByUserName(auth.getName());
        Specification<User> spec = UserSpecificationBuilder.getUserSpec(search, principal.getFirm());
        return userService.getDtoFromUserList(userService.findAll(spec));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable("id") int id, Authentication auth) {
      if(userService.deleteUser(id, auth.getName())) {
          return "User with id: " + id + " deleted successfully";
      }
      return "User deletion unsuccessful";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public UserDto updateUser(@PathVariable("id") int id, @RequestBody UserDto userDto) {
       return userService.updateUser(id, userDto);
    }

}
