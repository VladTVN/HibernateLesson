package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToOne
//    private Author author;

    @ManyToMany
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "author_id"),inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Author> authorList;

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public Book(String name, List<Author> authorList) {
        this.name = name;
        this.authorList = authorList;
    }

    //    public Book(String name, Author author) {
//        this.name = name;
//        this.author = author;
//    }

//    public Author getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Author author) {
//        this.author = author;
//    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Book(String name) {
        this.name = name;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book() {
    }
}
