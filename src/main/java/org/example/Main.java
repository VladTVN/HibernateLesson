package org.example;

import org.example.entity.Author;
import org.example.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UniversalDao universalDao = new UniversalDao();
        List<Book> books = universalDao.getBooks();

        Author author = new Author("2","2");
        Book book = new Book("1");

//        Book book = universalRepository.getBookById(31L);
        List<Book> bookList = new ArrayList<>();
        List<Author> authorList = new ArrayList<>();
        bookList.add(book);
        authorList.add(author);
        author.setBookList(bookList);
       book.setAuthorList(authorList);

        boolean isSuccessfullyAuthor = universalDao.addAuthor(author);

    }
}