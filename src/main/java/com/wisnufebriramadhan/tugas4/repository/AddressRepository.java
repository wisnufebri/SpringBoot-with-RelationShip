package com.wisnufebriramadhan.tugas4.repository;

import com.wisnufebriramadhan.tugas4.model.Address;
import com.wisnufebriramadhan.tugas4.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    Address findById(int id);
    List<Address> findByAddress(String address, Sort sort);;
}

