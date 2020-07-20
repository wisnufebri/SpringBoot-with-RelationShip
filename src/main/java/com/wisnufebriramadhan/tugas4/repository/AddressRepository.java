package com.wisnufebriramadhan.tugas4.repository;

import com.wisnufebriramadhan.tugas4.model.Address;
import com.wisnufebriramadhan.tugas4.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, String> {

    User findById(int id);
    Address findByAddress(String address);
    List<Address> findByAddress(String address, Sort sort);
    List<Address> findUserByAddress(String address, Pageable page);
}

