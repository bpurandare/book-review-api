package com.tcs.bookreview.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book_review")
public class BookReview {

    @Id
    @Column(name = "review_id")
    private int reviewId;
    @Column(name = "review_content")
    private String reviewContent;
    @Column(name = "review_date")
    private Date reviewDate;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BookReview() {
    }

    public BookReview(int reviewId, String reviewContent, Date reviewDate, Book book, User user) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.reviewDate = reviewDate;
        this.book = book;
        this.user = user;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
