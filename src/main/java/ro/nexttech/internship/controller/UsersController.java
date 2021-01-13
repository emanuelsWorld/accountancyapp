package ro.nexttech.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.dto.UserDto;
import ro.nexttech.internship.filters.users.UserSpecificationBuilder;
import ro.nexttech.internship.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> searchUsers(@RequestParam(value = "search") String search) {
        Specification<User> spec = UserSpecificationBuilder.getUserSpec(search);
        return userService.getDtoFromUserList(userService.findAll(spec));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("{/id}")
                    .buildAndExpand(userDto.getUserId(), userDto.getUserName(), userDto.getUserPassword())
                    .toUri();
            userService.saveUserDto(userDto);
            return ResponseEntity.created(uri).body(userDto);
        }
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable("id") int id) {
      if(userService.deleteUser(id)) {
          return "User with id: " + id + "deleted successfully";
      }
      return "User deletion unsuccessful";
    }

    @PutMapping("{id}")
    public UserDto updateUser(@PathVariable("id") int id, @RequestBody UserDto userDto) {
       return userService.updateUser(id, userDto);
    }


}
