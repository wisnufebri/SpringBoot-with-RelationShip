package com.wisnufebriramadhan.tugas4.service;


import com.wisnufebriramadhan.tugas4.model.Address;
import com.wisnufebriramadhan.tugas4.model.User;
import com.wisnufebriramadhan.tugas4.repository.AddressRepository;
import com.wisnufebriramadhan.tugas4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    public boolean delete(int id){
        Address result = addressRepository.findById(id);
        if (result != null){
            try{
                addressRepository.delete(result);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean updateAddress(Address body){
        Optional<Address> result = addressRepository.findById(body.getId());
        if (result != null) {
            try {
                addressRepository.save(body);
                return true;
            } catch (Exception e){
                return false;
            }
        } else {
            return false;
        }
    }


    public boolean addAddress(Address body) {
        Optional<User> userResult = userRepository.findById(body.getId());
        Address result = addressRepository.save(body);

        System.out.println("result" + userResult.toString());

        if (userResult != null){
            body.setUser(userResult);
            try{
                addressRepository.save(body);
                System.out.println("bisa" + userResult.toString());
                return true;
            } catch (Exception e ) {
                System.out.println("tidak bisa" + userResult.toString());
                return false;
            }
        } else {
            System.out.println("tidak bisa 2" + userResult.toString());
            return false;
        }
    }}
