package com.wisnufebriramadhan.tugas4.controller;

import com.wisnufebriramadhan.tugas4.model.BookCategory;
import com.wisnufebriramadhan.tugas4.repository.BookCategoryRepository;
import com.wisnufebriramadhan.tugas4.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookcategory/")
public class BookCategoryController {
    @Autowired
    BookCategoryService service;

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @GetMapping("")
    List<BookCategory> getBookCategory() {
        return (List<BookCategory>) bookCategoryRepository.findAll();
    }

    @PostMapping("insert")
    public BookCategory insertBookCategory(@RequestBody BookCategory body) {
        BookCategory result = bookCategoryRepository.save(body);
        return result;
    }

    @DeleteMapping("delete")
    Map<String, Object> deleteBookCategory(@RequestParam int id) {
        Map<String, Object> result = new HashMap<>();
        if (service.deleteBookCategory(id)) {
            result.put("Success", true);
        } else {
            result.put("Gagal", false);
        }
        return result;
    }

    @PutMapping("update")
    Map<String, Object> updateBookCategory(@RequestBody BookCategory body) {
        System.out.println("body : " + body.toString());
        Map<String, Object> result = new HashMap<>();
        if (service.updateBookCategory(body)) {
            result.put("succes", true);
            result.put("message", "berhasil");
        } else {
            result.put("succes", false);
            result.put("message", "gagal");
        }
        return result;
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            List<BookCategory> book = new ArrayList<BookCategory>();
            Pageable paging = PageRequest.of(page, size);

            Page<BookCategory> pagebook;
            if (search == null) {
                pagebook = bookCategoryRepository.findAll(paging);
            } else {
                pagebook = bookCategoryRepository.findByNameContaining(search, paging);
            }

            book = pagebook.getContent();

            if (book.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            } else {

                Map<String, Object> result = new HashMap<>();
                result.put("user", book);
                result.put("currentPage", pagebook.getNumber());
                result.put("totalItems", pagebook.getTotalElements());
                result.put("totalPages", pagebook.getTotalPages());


                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
