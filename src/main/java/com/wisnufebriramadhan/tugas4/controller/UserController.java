package com.wisnufebriramadhan.tugas4.controller;

import com.wisnufebriramadhan.tugas4.model.User;
import com.wisnufebriramadhan.tugas4.repository.UserRepository;
import com.wisnufebriramadhan.tugas4.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    //take data from package repository interface UserRepository
    @Autowired
    UserRepository userRepository;

    //find serive to here
    @Autowired
    UserService service;

    //get function for password

    //take all data from table user
    @GetMapping("/")
    public List<User> all() {
        return userRepository.findAll();
    }

    //take data from table by something
    @GetMapping("/getBy")
    User findByUsername(@RequestParam String username) {

        User result = service.findByUserName(username);
        return result;
    }

    //add or insert new data to table
    @PostMapping("/add")
    public User add(@RequestBody User body) {

        User result = userRepository.save(body);
        return result;
    }

    //edit or update data from table
    @PutMapping("/edit")
    public Map<String, Object> edit(@RequestBody User body) {
        System.out.println("body : " + body.toString());
        Map<String, Object> result = new HashMap<>();


        if (service.edit(body)) {
            result.put("success", true);
            result.put("message", "berhasil teredit");
        } else {
            result.put("success", false);
            result.put("message", "gagal teredit");
        }
        return result;
    }

    //delete data from table
    @DeleteMapping("/delete")
    Map<String, Object> delete(@RequestParam int id) {
        Map<String, Object> result = new HashMap<>();
        if (service.deleteUser(id)) {
            result.put("Berhasil", true);
        } else {
            result.put("Gagal", false);
        }
        return result;
    }
}
