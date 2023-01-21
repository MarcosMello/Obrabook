package com.projetopoo.rest;

import com.projetopoo.document.User;
import com.projetopoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserREST {
    @Autowired
    private UserService service;

    @PostMapping("/users")
    public User create(@RequestBody User user){
        return service.create(user);
    }

    @GetMapping("/users")
    public List<User> showUsers(){
        return service.showUsers();
    }

    @GetMapping("/user/{userID}")
    public User showUser(@PathVariable long userID){
        return service.showUser(userID);
    }

    @DeleteMapping("/user/{userID}")
    public String delete(@PathVariable long userID){
        return service.delete(userID);
    }
}
