package com.wisnufebriramadhan.tugas4.service;

import com.wisnufebriramadhan.tugas4.model.BookCategory;
import com.wisnufebriramadhan.tugas4.repository.BookCategoryRepository;
import com.wisnufebriramadhan.tugas4.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCategoryService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    public boolean deleteBookCategory(int id){
        BookCategory result = bookCategoryRepository.findById(id);
        if (result != null){
            try{
                bookCategoryRepository.delete(result);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean updateBookCategory(BookCategory body){
        BookCategory result = bookCategoryRepository.findById(body.getId());
        if (result != null) {
            try {
                bookCategoryRepository.save(body);
                return true;
            } catch (Exception e){
                return false;
            }
        } else {
            return false;
        }
    }

}
