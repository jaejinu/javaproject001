package kr.co.jaejinuproject001.myrestfulservice.controller;

import kr.co.jaejinuproject001.myrestfulservice.bean.User;
import kr.co.jaejinuproject001.myrestfulservice.dao.UserDaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public User retrieveUser(@PathVariable Integer id) {
        return service.findOne(id);
    }
}
