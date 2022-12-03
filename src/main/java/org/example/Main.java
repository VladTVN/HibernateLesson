package org.example;

import org.example.entity.Book;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        BookHelper bookHelper = new BookHelper();
        List<Book> books = bookHelper.getBooks();
        boolean isSuccessfully = bookHelper.addBook(new Book("123"));
        System.out.println(books.toString());
    }
}