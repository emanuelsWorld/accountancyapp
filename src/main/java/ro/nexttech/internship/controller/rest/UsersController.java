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

    @GetMapping
    public List<UserDto> searchUsers(@RequestParam(value = "search") String search, @RequestParam(value = "sort" , defaultValue = "No sorting") String sort) {
        System.out.println("Search:"+ search);
        System.out.println("Sorting:" + sort);
        Specification<User> spec = UserSpecificationBuilder.getUserSpec(search);
        return UserDto.getDtoFromUserList(repository.findAll(spec));
    }

  //  @GetMapping
   // public User getUser(@PathVariable("id") int id) {
    //    return null;
   // }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("{/id}")
                    .buildAndExpand(user.getUserId(), user.getUserName(), user.getUserPassword())
                    .toUri();
            System.out.println(user.getUserName());
            return ResponseEntity.created(uri).body(user);
        }
    }

    @DeleteMapping
    public String deleteUser() {
        return null;
    }

    @PutMapping("/{id}")
    public String updateUser() {
        return null;
    }


}
