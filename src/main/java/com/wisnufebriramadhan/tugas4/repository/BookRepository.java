package com.wisnufebriramadhan.tugas4.repository;


import com.wisnufebriramadhan.tugas4.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);

    Book findById(int id);

    Page<Book> findByTitleContaining(String search, Pageable pageable);
}
