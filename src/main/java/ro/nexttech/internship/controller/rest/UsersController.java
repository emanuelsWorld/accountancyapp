package ro.nexttech.internship.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.domain.UserRole;
import ro.nexttech.internship.dto.UserDto;
import ro.nexttech.internship.filters.users.UserRepository;
import ro.nexttech.internship.filters.users.UserSpecificationBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UsersController {

    @Autowired
    private UserRepository repository;

    @GetMapping("")
    public List<UserDto> searchUsers(@RequestParam(value = "search") String search) {
        Specification<User> spec = UserSpecificationBuilder.getUserSpec(search);
        return UserDto.getDtoFromUserList(repository.findAll(spec));
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("{/id}")
                    .buildAndExpand(user.getUserId(), user.getUserName(), user.getPassword())
                    .toUri();
            System.out.println(user.getUserName());
            return ResponseEntity.created(uri).body(user);
        }
    }

    @DeleteMapping("/delete")
    public String deleteUser() {
        return null;
    }

    @PutMapping("update")
    public String updateUser() {
        return null;
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
