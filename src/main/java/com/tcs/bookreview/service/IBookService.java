package com.tcs.bookreview.service;

import com.tcs.bookreview.model.Book;
import com.tcs.bookreview.model.BookReview;
import com.tcs.bookreview.model.Search;
import com.tcs.bookreview.model.User;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
    List<Book> getBooksForPage(List<Book> books, int page);
    int getTotalPages(List<Book> books);
    List<Book> searchBooks(List<Book> books, Search search);
    void sortReviews(Book book);
    void addBook(Book book);
    void addReview(Book book, User user, BookReview review);
}
