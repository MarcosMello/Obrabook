package com.projetopoo.rest;

import com.projetopoo.document.User;
import com.projetopoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

    @GetMapping("/user/email/{email}")
    public List<User> showUsersByEmail(@PathVariable String email) { return service.showUsersByEmail(email); }

    @GetMapping("/user/{userID}")
    public User showUser(@PathVariable long userID){
        return service.showUser(userID);
    }

    @PutMapping("/user")
    public User update(@RequestBody User user){
        return service.update(user);
    }

    @DeleteMapping("/user/{userID}")
    public String delete(@PathVariable long userID){
        return service.delete(userID);
    }
}
