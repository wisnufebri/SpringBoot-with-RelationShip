package com.wisnufebriramadhan.tugas4.controller;

import com.wisnufebriramadhan.tugas4.model.Book;
import com.wisnufebriramadhan.tugas4.model.BookCategory;
import com.wisnufebriramadhan.tugas4.repository.BookRepository;
import com.wisnufebriramadhan.tugas4.service.BookService;
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
@RequestMapping("/api/book/")
public class BookController {

    @Autowired
    BookService service;

    @Autowired
    BookRepository bookRepository;


    @GetMapping("")
    List<Book> getBook(){
        return (List<Book>) bookRepository.findAll();
    }

    @PostMapping("insert")
    Map<String, Object> insertBook (@RequestBody Book body) {
        Map<String, Object> result = new HashMap<>();
        if (service.saveBody(body)) {
            result.put("Success", true);
            result.put("message", "berhasil");
        } else {
            result.put("Gagal", false);
            result.put("message", "gagal");
        }
        return result;
    }

    @DeleteMapping("delete")
    Map<String, Object> deleteBook(@RequestParam int id) {
        Map<String, Object> result = new HashMap<>();
        if (service.deleteBook(id)) {
            result.put("Success", true);
        } else {
            result.put("Gagal", false);
        }
        return result;
    }

    @PutMapping("update")
    Map<String, Object> updateBook(@RequestBody Book body) {
        System.out.println("body : " + body.toString());
        Map<String, Object> result = new HashMap<>();
        if (service.updateBook(body)) {
            result.put("succes", true);
            result.put("message", "berhasil");
        } else {
            result.put("succes", false);
            result.put("message", "gagal");
        }
        return result;
    }

    @GetMapping("byTitle")
    Book findByTitle(@RequestParam String title) {
        return service.findByTitle(title);

    }

    @GetMapping("byCategory")
    BookCategory findByCategory(@RequestParam int id) {
        return service.findByCategory(id);

    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            List<Book> book = new ArrayList<Book>();
            Pageable paging = PageRequest.of(page, size);

            Page<Book> pagebook;
            if (title == null) {
                pagebook = bookRepository.findAll(paging);
            } else {
                pagebook = bookRepository.findByTitleContaining(title, paging);
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