package com.tcs.bookreview.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Book {

    @Id
    @Column(name = "book_id")
    private Long bookId;
    private String isin;
    @Column(name = "book_name")
    private String name;
    @Column(name = "book_author")
    private String author;
    private String publisher;
    private String tags;
    @OneToMany(targetEntity = BookReview.class, mappedBy = "book", fetch = FetchType.EAGER)
    private List<BookReview> bookReviews;

    public Book() {
    }

    public Book(Long bookId, String isin, String name, String author, String publisher, String tags) {
        this.bookId = bookId;
        this.isin = isin;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.tags = tags;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<BookReview> getBookReviews() {
        return bookReviews;
    }

    public void setBookReviews(List<BookReview> bookReviews) {
        this.bookReviews = bookReviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return bookId.equals(book.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }
}
