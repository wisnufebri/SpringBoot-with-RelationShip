package com.latihan2.latihan2.controller;

import com.latihan2.latihan2.model.Login;
import com.latihan2.latihan2.model.User;
import com.latihan2.latihan2.repository.UserRepository;
import com.latihan2.latihan2.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @Autowired
    PasswordEncoder passwordEncoder;

    //take all data from table user
    @GetMapping("/")
    public List<User> all() {
        return userRepository.findAll();
    }

    //take data from table by something
    @GetMapping("/getBy")
    User findByUsername(@RequestParam String username){

        User result = service.findByUserName(username);
        return result;
    }

    //add or insert new data to table
    @PostMapping("/add")
    public User add(@RequestBody User body) {

        String hashedPassword = passwordEncoder.encode(body.getPassword());
        body.setPassword(hashedPassword);

        User result = userRepository.save(body);
        return result;
    }

    //edit or update data from table
    @PutMapping("/edit")
    public Map<String, Object> edit(@RequestBody User body) {
        System.out.println("body : " + body.toString());
        Map<String, Object> result = new HashMap<>();

        String hashedPassword = passwordEncoder.encode(body.getPassword());
        body.setPassword(hashedPassword);

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
    Map <String, Object> delete(@RequestParam int id) {
        Map<String, Object> result = new HashMap<>();
        if (service.deleteUser(id)){
            result.put("Berhasil", true);
        } else {
            result.put("Gagal", false);
        }
        return result;
    }

    //login take data authentication with username and password from DB
    @PostMapping("/login")
    public Map<String, Object> loginUser(@RequestBody Login body) {
        System.out.println(body.toString());
        User result = userRepository.findByUsername(body.getUsername());
        Map<String, Object> resultMap = new HashMap<>();

        if (result != null) {

            boolean isMatch = passwordEncoder.matches(body.getPassword(), result.getPassword());

            if (isMatch) {
                String token = Jwts.builder()
                        .setSubject(body.getUsername())
                        .claim("role", result.getRoles())
                        .signWith(SignatureAlgorithm.HS256, "1234")
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 8640000))
                        .compact();
                resultMap.put("success", true);
                resultMap.put("record", result);
                resultMap.put("token", token);
            }
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }
}
