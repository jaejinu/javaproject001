package kr.co.jaejinuproject001.myrestfulservice.controller;

import kr.co.jaejinuproject001.myrestfulservice.bean.User;
import kr.co.jaejinuproject001.myrestfulservice.dao.UserDaoService;
import kr.co.jaejinuproject001.myrestfulservice.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }


    @GetMapping("/users/{id}")
     public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user;

    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        User deletedUser = service.deleteById(id);

        if(deletedUser == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return ResponseEntity.noContent().build();
    }


}
