package com.tcs.bookreview.service;

import com.tcs.bookreview.model.Book;

import java.util.List;

public interface IPageService {
    List<Book> getBookForPage(List<Book> books, int page);
    int getTotalPages(int resultSize);
}
