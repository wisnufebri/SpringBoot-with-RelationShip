package com.wisnufebriramadhan.tugas4.repository;

import com.wisnufebriramadhan.tugas4.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, String> {


    User findById(int id);
    User findByUsername(String username);

    List<User> findByUsername(String username, Pageable pageable);
    List<User> findByName(String name, Sort sort);

    void deleteById(int id);

//    Page<User> findByUsername(String username, Pageable pageable);
//
//    Page<User> findbyName(String name, Pageable pageable);
}
