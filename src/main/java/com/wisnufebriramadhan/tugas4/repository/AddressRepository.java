package com.wisnufebriramadhan.tugas4.repository;

import com.wisnufebriramadhan.tugas4.model.Address;
import com.wisnufebriramadhan.tugas4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {

    User findById(int id);
    User findByAddress(String address);
}
