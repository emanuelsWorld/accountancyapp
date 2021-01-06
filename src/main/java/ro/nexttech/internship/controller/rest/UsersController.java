package ro.nexttech.internship.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.nexttech.internship.domain.User;

import java.net.URI;

@RestController
@RequestMapping("/rest/users")
public class UsersController {

    //TODO insert service

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        return null;
    }

    @PutMapping("/create")
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

    @PostMapping("update")
    public String updateUser() {
        return null;
    }
}
