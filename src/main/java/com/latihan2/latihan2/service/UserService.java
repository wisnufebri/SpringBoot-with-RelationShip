package com.latihan2.latihan2.service;



import com.latihan2.latihan2.model.User;
import com.latihan2.latihan2.repository.UserRepository;
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

    public User insertUsers(User body) {

        User result = usersRepository.save(body);

        return result;
    }

    public boolean updateSiswa(User body) {
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