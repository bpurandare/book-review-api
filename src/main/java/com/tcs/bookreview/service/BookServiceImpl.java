package com.tcs.bookreview.service;

import com.tcs.bookreview.dao.BookDao;
import com.tcs.bookreview.dao.BookReviewDao;
import com.tcs.bookreview.model.Book;
import com.tcs.bookreview.model.BookReview;
import com.tcs.bookreview.model.Search;
import com.tcs.bookreview.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService{

    @Autowired
    private BookDao bookDao;
    @Autowired
    private BookReviewDao reviewDao;
    @Autowired
    private IPageService pageService;

    public static class BookComparator implements Comparator<Book>
    {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static class ReviewComparator implements Comparator<BookReview>
    {

        @Override
        public int compare(BookReview o1, BookReview o2) {
            return o2.getReviewDate().compareTo(o1.getReviewDate());
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookDao.findAll().forEach(books::add);
        Collections.sort(books, Comparator.comparing(Book::getName));
        return books;
    }

    @Override
    public List<Book> getBooksForPage(List<Book> books, int page) {
        return pageService.getBookForPage(books, page);
    }

    @Override
    public int getTotalPages(List<Book> books) {
        if(books==null){
            return 1;
        }
        return pageService.getTotalPages(books.size());
    }

    @Override
    public List<Book> searchBooks(List<Book> books, Search search) {

        Set<Book> resultSet = new HashSet<>();

        if(books!=null) {
            boolean previousFieldExists = false;

            if (search.getBookName() != null && !search.getBookName().isEmpty()) {
                previousFieldExists = true;
                books.stream().filter(book -> book.getName().toLowerCase().contains(search.getBookName().toLowerCase())).forEach(resultSet::add);
            }

            if (search.getAuthor() != null && !search.getAuthor().isEmpty()) {

                List<Book> authorResults = new ArrayList<>();

                if(!previousFieldExists)
                    books.stream().filter(book -> book.getAuthor().toLowerCase().contains(search.getAuthor().toLowerCase())).forEach(resultSet::add);
                else if(!resultSet.isEmpty()) {
                    resultSet.stream().filter(book -> book.getAuthor().toLowerCase().contains(search.getAuthor().toLowerCase())).forEach(authorResults::add);
                    resultSet.addAll(authorResults);
                }

                previousFieldExists = true;
            }

            if (search.getPublisher() != null && !search.getPublisher().isEmpty()) {

                List<Book> publisherResults = new ArrayList<>();

                if(!previousFieldExists)
                    books.stream().filter(book -> book.getPublisher().toLowerCase().contains(search.getPublisher().toLowerCase())).forEach(resultSet::add);
                else if(!resultSet.isEmpty()) {
                    resultSet.stream().filter(book -> book.getPublisher().toLowerCase().contains(search.getPublisher().toLowerCase())).forEach(publisherResults::add);
                    resultSet.addAll(publisherResults);
                }

                previousFieldExists = true;
            }

            if (search.getTags() != null && !search.getTags().isEmpty()) {

                List<Book> tagsResults = new ArrayList<>();

                if(!previousFieldExists)
                    books.stream().filter(book -> book.getTags().toLowerCase().contains(search.getTags().toLowerCase())).forEach(resultSet::add);
                else if(!resultSet.isEmpty()) {
                    resultSet.stream().filter(book -> book.getTags().toLowerCase().contains(search.getTags().toLowerCase())).forEach(tagsResults::add);
                    resultSet.addAll(tagsResults);
                }
            }
        }

        List<Book> result = resultSet.stream().collect(Collectors.toList());
        Collections.sort(result, new BookComparator());

        return result;
    }

    @Override
    public void sortReviews(Book book) {
        Collections.sort(book.getBookReviews(), new ReviewComparator());
    }

    @Override
    public void addBook(Book book) {
        long count = bookDao.count();
        book.setBookId(++count);
        bookDao.save(book);
    }

    @Override
    public void addReview(Book book, User user, BookReview review) {
        int count = Math.toIntExact(reviewDao.count());
        review.setReviewId(++count);
        review.setReviewDate(new Date());
        review.setBook(book);
        review.setUser(user);
        book.getBookReviews().add(review);
        sortReviews(book);
        reviewDao.save(review);
    }

}
