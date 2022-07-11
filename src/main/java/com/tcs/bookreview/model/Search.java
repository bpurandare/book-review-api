package com.tcs.bookreview.model;

public class Search {

    private String bookName;
    private String author;
    private String publisher;
    private String tags;

    public Search() {
    }

    public Search(String bookName, String author, String publisher, String tags) {
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.tags = tags;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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
}
