package com.wisnufebriramadhan.tugas4.service;


import com.wisnufebriramadhan.tugas4.model.Address;
import com.wisnufebriramadhan.tugas4.model.User;
import com.wisnufebriramadhan.tugas4.repository.AddressRepository;
import com.wisnufebriramadhan.tugas4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository usersRepository;

    public User getUserById(int id) {
        return usersRepository.findById(id);
    }

    public List<User> getAllUser(Integer pageNo, String sortKey) {
        int noOfRecord = 5;
        Pageable page = PageRequest.of(pageNo, noOfRecord, Sort.by(sortKey));
        Page<User> pagedResult = usersRepository.findAll(page);
        return pagedResult.getContent();
    }

    public List<Address> getAddress(String address) {
        Sort sortKey = Sort.by("address");
        return addressRepository.findByAddress(address, sortKey);
    }

    public List<User> getNameuser(String name) {
        Sort sortKey = Sort.by("name");
        return usersRepository.findByName(name, sortKey);
    }

    public User findByUserName(String username) {
        return usersRepository.findByUsername(username);
    }


    public boolean delete(int id){
        User result = usersRepository.findById(id);
        if (result != null){
            try{
                usersRepository.delete(result);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public User saveBody(User user) {
        User user1 = null;
        Address address1 = user.getAddress();
        try {
            user.setAddress(null);
            user1 = usersRepository.save(user);
            address1.setUser(user1);
            addressRepository.save(address1);
            System.out.println("input success" + address1);
            return user;
        } catch (Exception e) {
            return user1;
        }
    }

    public User add(User body) {

        User result = usersRepository.save(body);

        return result;
    }

    public boolean edit(User body) {
        User result = usersRepository.findById(body.getId());
        if (result != null) {
            try {
                usersRepository.save(body);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

}