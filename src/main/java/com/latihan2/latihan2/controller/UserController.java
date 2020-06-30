package com.latihan2.latihan2.controller;

import com.latihan2.latihan2.model.AuthenticateModel;
import com.latihan2.latihan2.model.User;
import com.latihan2.latihan2.repository.UserRepository;
import com.latihan2.latihan2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    //take data from package repository interface UserRepository
    @Autowired
    UserRepository userRepository;

    //take all data from table user
    @GetMapping("/")
    public List<User> all() {
        return userRepository.findAll();
    }

    //add or insert new data to table
    @PostMapping("/add")
    public User add(@RequestBody User body) {
        User result = userRepository.save(body);
        return result;
    }

    //edit or update data from table
    @PutMapping("/{id}")
    public User update(@PathVariable("id") int id,
                       @RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam("name") String name) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);

        return userRepository.save(user);
    }

    //delete data from table
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        User user = new User();
        user.setId(id);

        userRepository.delete(user);
    }

    //take data from table by something
    @GetMapping("/{id}")
    public User find(@PathVariable("id") int id) {
        return userRepository.findById(id);
    }

    //login take data authentication with username and password from DB
    @PostMapping("/login")
    public Map<String, Object> loginUser(@RequestBody AuthenticateModel authBody) {
        System.out.println(authBody.toString());
        Map<String, Object> resultMap = new HashMap<>();
        User result = userRepository.findByUsername(authBody.getUsername());

        if (result != null) {

            System.out.println("user ada");

            if (result.getPassword().equals(authBody.getPassword())) {
                resultMap.put("success", true);
                resultMap.put("record", result);
            } else {
                resultMap.put("success", false);
            }
        } else {
            System.out.println("user tidak ada");
            resultMap.put("success", false);

        }

        return resultMap;
    }
}
