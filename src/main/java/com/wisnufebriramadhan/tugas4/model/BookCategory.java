package com.wisnufebriramadhan.tugas4.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bookcategory")
@Access(value = AccessType.FIELD)
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;

    @OneToMany(
            mappedBy = "bookcategory",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<Book> book;

    public BookCategory() {
    }

    public BookCategory(int id, String name, Set<Book> book) {
        this.id = id;
        this.name = name;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBook() {
        return book;
    }

    public void setBook(Set<Book> book) {
        this.book = book;
    }
}

