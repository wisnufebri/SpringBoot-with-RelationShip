package com.latihan2.tugas4.service;


import com.latihan2.tugas4.model.User;
import com.latihan2.tugas4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository usersRepository;

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public User findByUserName(String username) {
        return usersRepository.findByUsername(username);
    }

    public User findByUserId(int id) {
        return usersRepository.findById(id);
    }

    public boolean deleteUser(int id) {
        User result = usersRepository.findById(id);
        if (result != null) {
            try {
                usersRepository.delete(result);
                return true;
            } catch (Exception E) {
                return false;
            }
        } else {
            return false;
        }
    }

    public User add(User body) {

        User result = usersRepository.save(body);

        return result;
    }

    public boolean edit(User body) {
        User result = usersRepository.findById(body.getId());

        if (result != null) {
            System.out.println("users ada");
            try {
                usersRepository.save(body);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            System.out.println("users tidak ada");
            return false;
        }
    }

}