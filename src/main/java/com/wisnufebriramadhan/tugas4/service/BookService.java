package com.wisnufebriramadhan.tugas4.service;

import com.wisnufebriramadhan.tugas4.model.Book;
import com.wisnufebriramadhan.tugas4.model.BookCategory;
import com.wisnufebriramadhan.tugas4.repository.BookCategoryRepository;
import com.wisnufebriramadhan.tugas4.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @Autowired
    AddressService adservice;

    public boolean deleteBook(int id) {
        Book result = bookRepository.findById(id);
        if (result != null) {
            try {
                bookRepository.delete(result);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean updateBook(Book body) {
        Book result = bookRepository.findById(body.getId());
        if (result != null) {
            try {
                bookRepository.save(body);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public Book findByTitle(String title) {
        Book result = bookRepository.findByTitle(title);
        return result;
    }

    public BookCategory findByCategory(int id) {
        BookCategory result = bookCategoryRepository.findById(id);
        return result;
    }

    public boolean saveBody(Book book) {
        Book book1;
        BookCategory bookCategory1 = book.getBookcategory();
        try {
            book.setBookcategory(bookCategory1);
            book1 = bookRepository.save(book);
            bookCategory1.setBook(Collections.singleton(book1));
            bookCategoryRepository.save(bookCategory1);
            System.out.println("input berhasil" + bookCategory1);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
