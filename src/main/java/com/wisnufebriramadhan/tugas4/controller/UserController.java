package com.wisnufebriramadhan.tugas4.controller;

import com.wisnufebriramadhan.tugas4.model.Address;
import com.wisnufebriramadhan.tugas4.model.User;
import com.wisnufebriramadhan.tugas4.repository.UserRepository;
import com.wisnufebriramadhan.tugas4.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService service;

    @GetMapping
    public List<User> getAllUser(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                 @RequestParam(value = "sortKey", defaultValue = "address") String sortKey) {
        return service.getAllUser(pageNo, sortKey);
    }

    @GetMapping("/address/{address}")
    public List<Address> getUserbyAddress(@PathVariable String address){
        return service.getAddress(address);
    }


    @DeleteMapping("delete")
    Map<String, Object> deleteUser(@RequestParam int id) {
        Map<String, Object> result = new HashMap<>();
        if (service.delete(id)) {
            result.put("Success", true);
        } else {
            result.put("Gagal", false);
        }
        return result;
    }


    @GetMapping("/getBy")
    User findByUsername(@RequestParam String username) {

        User result = service.findByUserName(username);
        return result;
    }

    @PostMapping("/add")
    public User add(@RequestBody User body) {
        return service.saveBody(body);
    }

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
}
