package com.tcs.bookreview.controller;

import com.tcs.bookreview.model.*;
import com.tcs.bookreview.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tcs.bookreview.service.ConstantsService.*;
import static com.tcs.bookreview.service.ControllerService.clearErrors;

@Controller
@SessionAttributes({ATTRIBUTE_PAGINATION, ATTRIBUTE_BOOKS, ATTRIBUTE_SEARCH, ATTRIBUTE_USER, ATTRIBUTE_LOGIN_RESULT, ATTRIBUTE_BOOK_REVIEW})
public class ResourceController {

    @Autowired
    private IBookService bookService;

    @ModelAttribute(ATTRIBUTE_PAGINATION)
    public Pagination pagination(){
        return new Pagination();
    }

    @ModelAttribute(ATTRIBUTE_BOOKS)
    public List<Book> books(){
        return null;
    }

    @ModelAttribute(ATTRIBUTE_USER)
    public User user(){
        return null;
    }

    @ModelAttribute(ATTRIBUTE_LOGIN_RESULT)
    public boolean isLoginSuccess(){
        return false;
    }

    @RequestMapping("/")
    public String getAllBooks(ModelMap modelMap, @ModelAttribute(ATTRIBUTE_USER) User user, @ModelAttribute(ATTRIBUTE_LOGIN_RESULT)boolean isLoginSuccessful){

        clearErrors(modelMap);

        Pagination pagination = new Pagination();
        List<Book> books = null;

        if(pagination.getPage()==0) {
            pagination.setPage(1);
        }

        if(books==null || books.isEmpty()){
            books = bookService.getAllBooks();
        }
        List<Book> pagedBooks = bookService.getBooksForPage(books, pagination.getPage());

        if(books.isEmpty()){
            modelMap.put(ERROR_MESSAGE, "No Books Found!");
        }

        pagination.setTotalPages(bookService.getTotalPages(books));
        pagination.setTotalRrecords(books.size());

        modelMap.put(ATTRIBUTE_PAGED_BOOKS, pagedBooks);
        modelMap.put(ATTRIBUTE_BOOKS, books);
        modelMap.put(ATTRIBUTE_PAGINATION, pagination);
        modelMap.put(ATTRIBUTE_PAGE, pagination.getPage());
        modelMap.put(ATTRIBUTE_TOTAL_PAGES, pagination.getTotalPages());
        modelMap.put(ATTRIBUTE_TOTAL_RECORDS, pagination.getTotalRrecords());
        modelMap.put(ATTRIBUTE_SEARCH, new Search());
        modelMap.put(ATTRIBUTE_USER, user);
        modelMap.put(ATTRIBUTE_LOGIN_RESULT, isLoginSuccessful);

        return "booklist";
    }

    @RequestMapping("/nav/{direction}")
    public String navigate(@PathVariable String direction, @ModelAttribute(ATTRIBUTE_BOOKS) List<Book> books, @ModelAttribute(ATTRIBUTE_PAGINATION)Pagination pagination
            , ModelMap modelMap)
    {
        List<Book> pagedBooks = new ArrayList<>();
        int page = pagination.getPage();
        if(NAV_PREV.equalsIgnoreCase(direction)){
            pagedBooks = bookService.getBooksForPage(books, --page);
        }else {
            pagedBooks = bookService.getBooksForPage(books, ++page);
        }
        pagination.setPage(page);

        modelMap.put(ATTRIBUTE_PAGED_BOOKS, pagedBooks);
        modelMap.put(ATTRIBUTE_PAGE, pagination.getPage());
        modelMap.put(ATTRIBUTE_TOTAL_PAGES, pagination.getTotalPages());
        modelMap.put(ATTRIBUTE_TOTAL_RECORDS, pagination.getTotalRrecords());

        return "booklist";
    }

    @RequestMapping("/reload")
    public String reload( @ModelAttribute(ATTRIBUTE_BOOKS) List<Book> books, @ModelAttribute(ATTRIBUTE_PAGINATION)Pagination pagination,ModelMap modelMap)
    {
        pagination.setPage(0);
        pagination.setTotalPages(0);
        modelMap.put(ATTRIBUTE_BOOKS, null);
        modelMap.put(ATTRIBUTE_PAGE, pagination.getPage());
        modelMap.put(ATTRIBUTE_TOTAL_PAGES, pagination.getTotalPages());
        modelMap.put(ATTRIBUTE_TOTAL_RECORDS, pagination.getTotalRrecords());
        return "redirect:/";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@ModelAttribute(ATTRIBUTE_PAGINATION) Pagination pagination, @ModelAttribute(ATTRIBUTE_SEARCH) Search search
            , ModelMap modelMap)
    {
        clearErrors(modelMap);

        pagination.setPage(1);

        List<Book> books = bookService.getAllBooks();
        books = bookService.searchBooks(books, search);
        List<Book> pagedBooks = bookService.getBooksForPage(books, pagination.getPage());

        if(books.isEmpty()){
            modelMap.put(ERROR_MESSAGE, "No Books Found for Search!");
        }

        pagination.setTotalPages(bookService.getTotalPages(books));
        pagination.setTotalRrecords(books.size());

        modelMap.put(ATTRIBUTE_PAGED_BOOKS, pagedBooks);
        modelMap.put(ATTRIBUTE_BOOKS, books);
        modelMap.put(ATTRIBUTE_PAGE, pagination.getPage());
        modelMap.put(ATTRIBUTE_TOTAL_PAGES, pagination.getTotalPages());
        modelMap.put(ATTRIBUTE_TOTAL_RECORDS, pagination.getTotalRrecords());
        modelMap.put(ATTRIBUTE_SEARCH, search);

        return "booklist";
    }

    @RequestMapping("/review")
    public String review(@RequestParam int id, @ModelAttribute(ATTRIBUTE_BOOKS) List<Book> books, ModelMap modelMap){
        Book book = books.stream().filter(b -> b.getBookId() == id).collect(Collectors.toList()).get(0);
        bookService.sortReviews(book);
        modelMap.put(ATTRIBUTE_BOOK_REVIEW, book);
        return "bookreview";
    }

    @RequestMapping("/back")
    public String back(@ModelAttribute(ATTRIBUTE_BOOKS) List<Book> books, @ModelAttribute(ATTRIBUTE_PAGINATION)Pagination pagination, ModelMap modelMap){
        int page = pagination.getPage();
        List<Book> pagedBooks = bookService.getBooksForPage(books,page);
        modelMap.put(ATTRIBUTE_PAGED_BOOKS, pagedBooks);
        modelMap.put(ATTRIBUTE_PAGE, pagination.getPage());
        modelMap.put(ATTRIBUTE_TOTAL_PAGES, pagination.getTotalPages());
        modelMap.put(ATTRIBUTE_TOTAL_RECORDS, pagination.getTotalRrecords());
        return "booklist";
    }

    @RequestMapping("/addBook")
    public String addBookForm(ModelMap modelMap){
        modelMap.put(ATTRIBUTE_NEW_BOOK, new Book());
        return "addbook";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute(ATTRIBUTE_NEW_BOOK)Book book, @ModelAttribute(ATTRIBUTE_BOOKS)List<Book> books
            , @ModelAttribute(ATTRIBUTE_PAGINATION)Pagination pagination, ModelMap modelMap)
    {
        bookService.addBook(book);
        books.add(book);

        int page = pagination.getPage();
        pagination.setTotalRrecords(books.size());
        pagination.setTotalPages(bookService.getTotalPages(books));

        List<Book> pagedBooks = bookService.getBooksForPage(books,page);

        modelMap.put(ATTRIBUTE_PAGED_BOOKS, pagedBooks);
        modelMap.put(ATTRIBUTE_PAGE, pagination.getPage());
        modelMap.put(ATTRIBUTE_TOTAL_PAGES, pagination.getTotalPages());
        modelMap.put(ATTRIBUTE_TOTAL_RECORDS, pagination.getTotalRrecords());

        return "booklist";
    }

    @RequestMapping("/addReview")
    public String addReviewForm(@ModelAttribute(ATTRIBUTE_BOOK_REVIEW)Book book, ModelMap modelMap){
        modelMap.put(ATTRIBUTE_REVIEW, new BookReview());
        modelMap.put(ATTRIBUTE_BOOK_REVIEW, book);
        return "addreview";
    }

    @RequestMapping(value = "/addReview", method = RequestMethod.POST)
    public String addReview(@ModelAttribute(ATTRIBUTE_BOOK_REVIEW)Book book, @ModelAttribute(ATTRIBUTE_USER)User user
            , @ModelAttribute(ATTRIBUTE_REVIEW)BookReview review, ModelMap modelMap)
    {
        bookService.addReview(book, user, review);
        modelMap.put(ATTRIBUTE_BOOK_REVIEW, book);
        return "bookreview";
    }

}
